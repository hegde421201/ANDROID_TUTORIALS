package com.newstudies.androidtutorials.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newstudies.androidtutorials.R
import com.newstudies.androidtutorials.model.MovieModel
import com.newstudies.androidtutorials.utils.getKey
import com.newstudies.androidtutorials.utils.getProgressDrawable
import com.newstudies.androidtutorials.utils.getUrl
import com.newstudies.androidtutorials.utils.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private var movieList: MovieModel?) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private lateinit var imageUrlConstant : String

    fun updateMovieList(newMovieList: MovieModel){
        movieList = newMovieList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie,parent,false)

        imageUrlConstant = getUrl(parent.context)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
      holder.view.textMovieName2.text = movieList?.results?.get(position)?.title

      val imageUrl = imageUrlConstant + movieList?.results?.get(position)?.posterImage

      holder.view.imageMoviePortrait2.loadImage(imageUrl,
                                    getProgressDrawable(holder.view.context),holder.view.context)
    }

    override fun getItemCount(): Int = movieList?.results?.size?:0

    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}