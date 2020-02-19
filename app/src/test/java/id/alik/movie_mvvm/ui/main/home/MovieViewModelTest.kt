package id.alik.movie_mvvm.ui.main.home

import androidx.lifecycle.ViewModelProviders
import id.alik.movie_mvvm.common.utils.Constants
import id.alik.movie_mvvm.service.RestRepository
import id.alik.movie_mvvm.service.ViewModelFactory
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel(RestRepository.createApiRepository())
    }

    @Test
    fun getMovieData() {
        viewModel.getMovie()
        val movieEntities = viewModel.movieData
        assertNotNull(movieEntities)
        assertEquals(true, movieEntities.value?.results?.size ?:0 > 0)
    }
}