package com.example.moviepartiel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepartiel.item.MovieItem
import com.example.moviepartiel.model.Movie
import com.example.moviepartiel.network.UrlBuilder
import com.example.moviepartiel.network.services.HttpRequest
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Valentin Pigeau on 14/04/2019
 */

class MainActivity : AppCompatActivity() {

    companion object {

        /*
        * Definition
         */
        val moviesAdapter = FastItemAdapter<MovieItem>()

        fun injectMoviesInView(responseData: String?, moviesRecyclerView: RecyclerView, ctx: Context) {

            val arrayData = JSONObject(responseData).getJSONArray("results")

            moviesAdapter.clear()

            //add movies in moviesAdaptater
            for (i in 0 until arrayData.length()) {
                var movie = Movie.movieFactory(arrayData.getJSONObject(i))
                moviesAdapter.add(MovieItem(movie))
            }

            //Inject moviesAdaptater in View
            moviesRecyclerView.adapter = moviesAdapter
            moviesRecyclerView.layoutManager = LinearLayoutManager(ctx)
            moviesRecyclerView.addItemDecoration(DividerItemDecoration(ctx, RecyclerView.VERTICAL))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * clickListener on Movies Cells
         */
        moviesAdapter.withOnClickListener { view, adapter, item, position ->
            val intent = MovieDetailActivity.createIntent(this, item.movie)
            startActivity(intent)

            true
        }

        /*
        * Request to get Movies for first ini
         */
        HttpRequest.GET(UrlBuilder.getUrlMovieField("id,description,image,name"), object : Callback {
            override fun onResponse(call: Call?, response: Response) {
                runOnUiThread {
                    try {
                        /*
                        * Success
                         */
                        injectMoviesInView(response.body()?.string(), moviesRecyclerView, this@MainActivity)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("httpGetRequest", "Request Failure.")
            }
        })


        /*
        *Text Change Listener on search Edit
        */
        searchEditText.addTextChangedListener {

            val url = UrlBuilder.getUrlMovieFilterField(arrayListOf("name", it.toString()),"id,description,image,name")

            HttpRequest.GET(url, object : Callback {
                override fun onResponse(call: Call?, response: Response) {
                    runOnUiThread {
                        try {
                            /*
                            * Success
                             */
                            injectMoviesInView(response.body()?.string(), moviesRecyclerView, this@MainActivity)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("httpGetRequest", "Request Failure.")
                }
            })
        }

    }
}
