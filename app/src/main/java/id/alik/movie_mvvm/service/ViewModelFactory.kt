package id.alik.movie_mvvm.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TRAILER_TYPE
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TYPE
import id.alik.movie_mvvm.common.utils.Constants.TV_TRAILER_TYPE
import id.alik.movie_mvvm.common.utils.Constants.TV_TYPE
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.ui.main.home.MovieViewModel
import id.alik.movie_mvvm.ui.main.detail.DetailMovieViewModel
import id.alik.movie_mvvm.ui.main.tvshow.TvViewModel

class ViewModelFactory(private val repoApi: ApiServiceManager, private val repoType : String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(repoType){
            TV_TYPE -> TvViewModel(repoApi) as T
            MOVIE_TYPE -> MovieViewModel(repoApi) as T
            MOVIE_TRAILER_TYPE , TV_TRAILER_TYPE -> DetailMovieViewModel(
                repoApi
            ) as T
            else -> MovieViewModel(repoApi) as T
        }
    }
}