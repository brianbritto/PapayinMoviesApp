package com.brianbritto.papayinmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianbritto.papayinmovies.model.Movie
import androidx.recyclerview.widget.RecyclerView
import com.brianbritto.papayinmovies.R
import com.brianbritto.papayinmovies.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun update(data: List<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) {
            with(itemView) {
                Glide.with(imvMovieBackdrop.context)
                    .load("${Constants.API_BASE_URL_IMAGE_W500}${item.backdropPath}")
                    .into(imvMovieBackdrop)
                txtMovieTitle.text = item.title
                txtMovieRating.text = "${item.voteAverage}"
                txtMovieVote.text = "${item.voteCount}"
                txtMovieDescription.text = "${item.overview}"
            }
        }

    }
}