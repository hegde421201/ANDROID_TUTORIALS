package com.newstudies.androidtutorials.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.newstudies.androidtutorials.model.MovieModel
import com.newstudies.androidtutorials.model.MoviesApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


//this connects the view with the model (MovieModel)
//this viewmodel exposes a series of livedata variables-instantiate them
// and connect the view objects to them to retrieve information

//AndroidViewModel is the super class which the viewmodel inherits from it
class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    //lazy means that the variable will be instantiated on need. Here creation is deferred until and unless used
    //LiveData is an observable that provides info to the observers
    val moviesList by lazy {MutableLiveData<MovieModel>()}

    //Textview displays text with error message when something goes wrong. If everything is OK then it disappears.
    val loadErrorView by lazy { MutableLiveData<Boolean>() }

    //Progress bar will be loading when the data retrieval is in progress while it becomes false when data retrieval is done
    val loadingProgressBar by lazy {MutableLiveData<Boolean>()}

    private val disposable = CompositeDisposable()
    private val apiService = MoviesApiService()

    fun retrieval(){
        getMoviesInfo()
    }

    private fun getMoviesInfo(){
        disposable.add(
            apiService.getMoviesBySearch(getApplication()) //call backend service
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieModel>(){
                    override fun onSuccess(data: MovieModel) {
                        loadingProgressBar.value = false
                        loadErrorView.value = true
                        Log.d("SIZE_DATA",data.results.size.toString())
                        moviesList.value = data
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.d("ERRORHTTP",e.localizedMessage)
                        loadErrorView.value = true
                        loadingProgressBar.value = false
                        moviesList.value = null
                    }



                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getMoviesRetrofit(){
        /*
         --- POJO class
         --- API interface
            - Specifies the endpoints used
            - Defines methods
            - @GET, @POST
         --- Service Class
            - specifies the base URL
            - Any required factory methods

            https://api.themoviedb.org/3/search/movie?api_key=76cd4e83faa948e8b559c1e4117d9f9f&query=simpsons
          */
    }

    /*

     private fun getMoviesInfo(){
        val m1 = MovieModel("Dangal")
        val m2 = MovieModel("Dabangg")
        val m3 = MovieModel("Sholay")
        val m4 = MovieModel("Titanic")
        val m5 = MovieModel("Khalnayak")
        val m6 = MovieModel("Junglee")

        val movies = arrayListOf(m1,m2,m3,m4,m5,m6)

        //the view model updates the mutable live data variables. It does not care about the view
        moviesList.value = movies
        loadErrorView.value = false
        loadingProgressBar.value = false
    }

    * */



}