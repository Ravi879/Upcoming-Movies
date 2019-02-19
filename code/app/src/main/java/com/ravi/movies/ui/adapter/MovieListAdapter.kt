package com.ravi.movies.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.movies.BR
import com.ravi.movies.R
import com.ravi.movies.domain.contract.RecyclerViewClickListener
import com.ravi.movies.domain.model.Movie


class MovieListAdapter(private val clickListener: RecyclerViewClickListener) :
    RecyclerView.Adapter<MovieListAdapter.RepositoryViewHolder>() {

    private val tag = "MovieListAdapterKT"

    private var movieList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewDataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.list_item_movie, parent, false)
        return RepositoryViewHolder(viewDataBinding, clickListener)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size


    fun setData(newMovieList: List<Movie>) {
        Log.v(tag, "newMovieList size.................. " + newMovieList.size)
        if (movieList.size > 0) {
            val postDiffCallback = PostDiffCallback(movieList, newMovieList)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)

            movieList.clear()
            movieList.addAll(newMovieList)
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            movieList = ArrayList(newMovieList)
            notifyDataSetChanged()
        }
    }


    class RepositoryViewHolder(
        private val viewDataBinding: ViewDataBinding,
        private val clickListener: RecyclerViewClickListener
    ) :
        RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        fun bind(movie: Movie) {
            viewDataBinding.setVariable(BR.mov, movie)
            viewDataBinding.setVariable(BR.movAdapter, this)
        }

        override fun onClick(view: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onItemClick(view!!, adapterPosition)
            }
        }

    }

    internal inner class PostDiffCallback(private val oldList: List<Movie>, private val newList: List<Movie>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id === newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }


}