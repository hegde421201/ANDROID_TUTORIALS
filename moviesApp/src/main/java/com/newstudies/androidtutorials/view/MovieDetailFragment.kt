package com.newstudies.androidtutorials.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newstudies.androidtutorials.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movie = MovieDetailFragmentArgs.fromBundle(it).results
        }

        context?.let {
            moviePoster.loadImage(getUrl(it) + movie?.posterImage, getProgressDrawable(it))
        }
         movieTitle.text =  movie?.title
         movieLanguage.text = "Language: ${movie?.language}"
    }



}