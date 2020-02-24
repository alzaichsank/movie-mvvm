package id.alik.movie_mvvm.ui.main.home

import id.alik.movie_mvvm.data.server.ApiServiceManager
import id.alik.movie_mvvm.service.RestRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @Mock
    lateinit var viewModel: MovieViewModel

    @Mock
    lateinit var apiServiceManager: ApiServiceManager

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(apiServiceManager)
    }

    @Test
    fun getMovieData() {
        viewModel.getMovie()
        val movieEntities = viewModel.movieData
        assertNotNull(movieEntities)
        assertEquals(true, movieEntities.value?.results?.size ?:0 > 0)
    }
}