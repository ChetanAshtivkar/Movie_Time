package com.chetan.movietime.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chetan.movietime.data.Movie
import com.chetan.movietime.databinding.ItemMovieBinding


/**
 * Created by Chetan on 2020-03-07.
 */
class FavouriteMoviesAdapter(
    private val listener: MovieClickListener,
    private val movieList: ArrayList<Movie> = ArrayList()
) : RecyclerView.Adapter<FavouriteMoviesAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    fun submitList(newList: List<Movie>) {
        val movieDiffUtilCallback = FavouriteMovieDiffCallback(movieList, newList)
        val diffResult = DiffUtil.calculateDiff(movieDiffUtilCallback)

        movieList.clear()
        movieList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item, listener, position)
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

internal class FavouriteMovieDiffCallback(
    private val oldItem: List<Movie>,
    private val newItem: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItem.size
    }

    override fun getNewListSize(): Int {
        return newItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition].id == newItem[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] == newItem[newItemPosition]
    }
}