package id.alik.movie_mvvm.ui.main.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.alik.movie_mvvm.BuildConfig
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse

class MovieViewModel (val apiServiceManager: ApiServiceManager) : ViewModel(){

    private val _movieData : MutableLiveData<MovieResponse> = MutableLiveData()
    var movieData : LiveData<MovieResponse> = _movieData
    var messageError : Throwable?= null

    @SuppressLint("CheckResult")
    fun getMovie() {
        apiServiceManager
            .emitterMovie(BuildConfig.MOVIEKEY)
            .doOnError {
                messageError = it
            }
            .subscribe {
                Logger.debug("cek load movie data ${it.results?.get(0)?.originalTitleMovie}")
                _movieData.postValue(it)
            }
    }
}