package com.chetan.movietime.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chetan.movietime.data.Movie
import com.chetan.movietime.databinding.ItemMovieBinding

/**
 * Created by Chetan on 2020-03-05.
 */
class MovieAdapter(private val listener: MovieClickListener) :
    PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    class MovieViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie?, listener: MovieClickListener, position: Int) {
            binding.movie = item
            binding.listener = listener
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

//class MovieListener(val clickListener: (Movie: Movie, position: Int) -> Unit) {
//    fun onClick(Movie: Movie, position: Int) = clickListener(Movie, position)
//}

interface MovieClickListener {
    fun onMovieClick(movie: Movie, position: Int)

    fun onFavouriteClick(movie: Movie, position: Int)
}
