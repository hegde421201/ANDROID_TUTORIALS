package com.newstudies.androidtutorials.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.newstudies.androidtutorials.R
import com.newstudies.androidtutorials.model.MovieModel
import com.newstudies.androidtutorials.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel : MovieListViewModel
    private val listAdapter = MovieListAdapter(null)

    private val movieListDataObserver = Observer<MovieModel>{
        data -> data?.let {
            movieListView.visibility = View.VISIBLE
            listAdapter.updateMovieList(it)

            if(it.results.isEmpty())
                textViewError.visibility = View.VISIBLE

            progressBarLoad.visibility = View.GONE
         }
    }

    private val loadingTextLiveDataObserver = Observer<Boolean>{
            isLoading -> progressBarLoad.visibility = if(isLoading) View.VISIBLE else View.GONE
            if(isLoading){
                movieListView.visibility = View.GONE
                textViewError.visibility = View.GONE
            }
    }

    private val errorLiveDataObserver = Observer<Boolean>{
            isError -> textViewError.visibility = if(isError) View.VISIBLE else View.GONE

            if(isError)progressBarLoad.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //stores the view model that is attached here taking care of lifecycle events
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        //observe live data variables
        viewModel.moviesList.observe(this.viewLifecycleOwner,movieListDataObserver)
        viewModel.loadErrorView.observe(this.viewLifecycleOwner,loadingTextLiveDataObserver)
        viewModel.loadingProgressBar.observe(this.viewLifecycleOwner,errorLiveDataObserver)

        //calling the retreval method to fetch
        viewModel.retrieval()

        //setting the gridview andd attaching the adapter to the recyclerview
        movieListView.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = listAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            movieListView.visibility = View.GONE
            textViewError.visibility = View.GONE
            progressBarLoad.visibility = View.VISIBLE
            viewModel.retrieval()
            swipeRefreshLayout.isRefreshing = false
        }
    }

}