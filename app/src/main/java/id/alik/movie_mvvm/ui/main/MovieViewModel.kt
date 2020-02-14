package id.alik.movie_mvvm.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.data.server.entity.response.MovieResponse

class MovieViewModel (val apiServiceManager: ApiServiceManager) : ViewModel(){

    private val _movieData : MutableLiveData<MovieResponse> = MutableLiveData()
    val movieData : LiveData<MovieResponse> = _movieData

    @SuppressLint("CheckResult")
    fun getMovie(keyword :String, apikey :String) {
        apiServiceManager
            .emitterMovie(keyword,apikey)
            .subscribe {
                _movieData.postValue(it)
            }
    }
}