package com.example.moviepartiel.item

import android.view.View
import com.bumptech.glide.Glide
import com.example.moviepartiel.R
import com.example.moviepartiel.model.Movie
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.cell_movie.view.*

/**
 * Based on work of Yvan Moté on 15/02/2019.
 * Edited by Valentin Pigeau on 14/04/2019
 * Class of a movie Item for view
 */

class MovieItem(val movie: Movie) : AbstractItem<MovieItem, MovieItem.MovieViewHolder>() {

    override fun getType(): Int {
        return R.id.movieTitleTextView
    }

    override fun getViewHolder(v: View): MovieViewHolder {
        return MovieViewHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.cell_movie
    }


    class MovieViewHolder(itemView: View) : FastAdapter.ViewHolder<MovieItem>(itemView) {

        override fun unbindView(item: MovieItem) {
            itemView.movieTitleTextView.text = null

        }

        override fun bindView(item: MovieItem, payloads: MutableList<Any>) {
            val movie = item.movie
            itemView.movieTitleTextView.text = movie.title
            Glide.with(itemView).load(movie.image["thumb_url"]).into(itemView.moviePosterView)

        }
    }
}