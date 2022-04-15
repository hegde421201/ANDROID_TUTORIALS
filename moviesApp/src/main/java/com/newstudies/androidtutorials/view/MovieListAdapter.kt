package com.newstudies.androidtutorials.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.newstudies.androidtutorials.R
import com.newstudies.androidtutorials.databinding.ItemMovieBinding
import com.newstudies.androidtutorials.model.MovieModel
import com.newstudies.androidtutorials.utils.getProgressDrawable
import com.newstudies.androidtutorials.utils.getUrl
import com.newstudies.androidtutorials.utils.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private var movieList: MovieModel?) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(), MovieClickListener {

    private lateinit var imageUrlConstant : String

    fun updateMovieList(newMovieList: MovieModel){
        movieList = newMovieList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

      //  val view = inflater.inflate(R.layout.item_movie,parent,false)

        val view = DataBindingUtil.inflate<ItemMovieBinding>(inflater,R.layout.item_movie,parent,false)
        imageUrlConstant = getUrl(parent.context)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
   //   holder.view.textMovieName2.text = movieList?.results?.get(position)?.title
        /*  holder.view.imageMoviePortrait2.loadImage(
                 imageUrl,
                 getProgressDrawable(holder.view.context)
             )*/

      holder.view.results = movieList?.results?.get(position)
      val imageUrl = imageUrlConstant + holder.view.results?.posterImage

      holder.view.listener = this

     /* holder.view.imageMoviePortrait2 = movieList

      holder.view.movieLayout.setOnClickListener {

          var result = movieList?.results?.get(position)

          val action = MovieListFragmentDirections.
          actionMovieListFragmentToMovieDetailFragment(result)
          Navigation.findNavController(holder.view).navigate(action)
      }*/
    }


    override fun getItemCount(): Int = movieList?.results?.size?:0

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
         movieList?.results?.let{
                for(value in it){
                    if(v.tag == value.title)
                    {
                        val action = MovieListFragmentDirections.
                        actionMovieListFragmentToMovieDetailFragment(value)
                        Navigation.findNavController(v).navigate(action)
                    }
                }
        }
    }
}

