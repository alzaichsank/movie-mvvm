package id.alik.movie_mvvm.ui.main.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.alik.movie_mvvm.BuildConfig
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvTrailerResponse

class DetailMovieViewModel (val apiServiceManager: ApiServiceManager) : ViewModel(){

    private val _movieTrailerData : MutableLiveData<MovieTrailerResponse> = MutableLiveData()
    var movieTrailerData : LiveData<MovieTrailerResponse> = _movieTrailerData
    private val _tvTrailerData : MutableLiveData<TvTrailerResponse> = MutableLiveData()
    var tvTrailerData : LiveData<TvTrailerResponse> = _tvTrailerData
    private lateinit var idMovie : String
    var messageError : Throwable?= null

    fun setIdMovie(id : String) { this.idMovie =id }

    @SuppressLint("CheckResult")
    fun getMovieTrailer() {
        apiServiceManager
            .emitterMovieTrailer(idMovie,BuildConfig.MOVIEKEY)
            .doOnError {
                messageError = it
            }
            .subscribe {
                Logger.debug("cek load movie data ${it.results?.get(0)?.nameTrailerMovie}")
                _movieTrailerData.postValue(it)
            }
    }

    @SuppressLint("CheckResult")
    fun getTvTrailer() {
        apiServiceManager
            .emitterTvTrailer(idMovie,BuildConfig.MOVIEKEY)
            .doOnError {
                messageError = it
            }
            .subscribe {
                Logger.debug("cek load movie data ${it.results?.get(0)?.nameTrailerTv}")
                _tvTrailerData.postValue(it)
            }
    }


}