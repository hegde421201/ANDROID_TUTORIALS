package com.newstudies.androidtutorials.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.newstudies.androidtutorials.R
import com.newstudies.androidtutorials.databinding.FragmentMovieDetailBinding
import com.newstudies.androidtutorials.model.MoviePalette
import com.newstudies.androidtutorials.model.Results
import com.newstudies.androidtutorials.utils.getProgressDrawable
import com.newstudies.androidtutorials.utils.getUrl
import com.newstudies.androidtutorials.utils.loadImage
import kotlinx.android.synthetic.main.fragment_movie_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    var movie: Results? = null
    private lateinit var dataBinding : FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_detail,container,false)

        // Inflate the layout for this fragment
        return dataBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movie = MovieDetailFragmentArgs.fromBundle(it).results
        }

        context?.let {
          //  dataBinding.moviePoster.loadImage(getUrl(it) + movie?.posterImage, getProgressDrawable(it))
            setBackGroundColor(getUrl(it)+movie?.posterImage)
        }
         movieTitle.text =  movie?.title
         movieLanguage.text = "Language: ${movie?.language}"

        dataBinding.results = movie
    }

    private fun setBackGroundColor(url: String) {
        Glide.with(this).asBitmap().load(url).into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                Palette.from(resource).generate {
                    palette ->
                    val intColor = palette?.lightMutedSwatch?.rgb ?:0
                   // dataBinding.movieDetailLayout.setBackgroundColor(intColor)
                    dataBinding.palette = MoviePalette(intColor)
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })
    }


}

