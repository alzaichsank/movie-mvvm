package id.alik.movie_mvvm.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.ui.main.home.MovieViewModel

class ViewModelFactory(private val repoApi: ApiServiceManager) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repoApi) as T
    }

}