package com.example.moviepartiel

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviepartiel.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Based on work of Yvan Moté on 15/02/2019.
 * Edited by Valentin Pigeau on 14/04/2019
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_MOVIE = "extra_movie"

        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        /*
        *Insert data in template
         */
        this.title = movie.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            descriptionTextView.text = Html.fromHtml(movie.description, Html.FROM_HTML_MODE_COMPACT)
        }else{
            descriptionTextView.text= removeHTML(movie.description)
        }
        Glide.with(this).load(movie.image["screen_large_url"]).into(largeImageView)


    }
}
