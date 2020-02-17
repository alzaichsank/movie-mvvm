package id.alik.movie_mvvm.ui.main.tvshow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.alik.movie_mvvm.BuildConfig
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvResponse

class TvViewModel (val apiServiceManager: ApiServiceManager) : ViewModel(){

    private val _tvData : MutableLiveData<TvResponse> = MutableLiveData()
    var tvData : LiveData<TvResponse> = _tvData

    @SuppressLint("CheckResult")
    fun getTv() {
        apiServiceManager
            .emitterTv(BuildConfig.MOVIEKEY)
            .subscribe {
                Logger.debug("cek load movie data ${it.results?.get(0)?.originalNameMovie}")
                _tvData.postValue(it)
            }
    }
}