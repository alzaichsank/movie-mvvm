package id.alik.movie_mvvm.ui.main.home

import alzaichsank.com.intentanimation.AnimIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.extension.makeGone
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_DATA
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TYPE
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieData
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse
import id.alik.movie_mvvm.service.RestRepository
import id.alik.movie_mvvm.service.ViewModelFactory
import id.alik.movie_mvvm.ui.main.home.adapter.AdapterMovie
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_shimmer_movie.*
import id.alik.movie_mvvm.ui.main.home.detail.DetailMovieActivity

class FragmentHome : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private var movieResponse: MovieResponse? = null
    private var movieData : MovieData?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(RestRepository.createApiRepository(), MOVIE_TYPE)
        ).get(
            MovieViewModel::class.java
        )
        movieViewModel.getMovie()
        initRecycler()

        movieViewModel.movieData.observe(requireActivity(), Observer {
            implementData(it)
            Logger.debug("cek load data ${it.results?.get(0)?.originalTitleMovie}")
        })
    }

    private fun initRecycler() {
        rv_home.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = AdapterMovie(requireActivity()) {
                movieData = getListMovie()?.getDataAt(it)
                val intent = Intent(requireActivity(), DetailMovieActivity::class.java).apply {
                    putExtra(INTENT_EXTRA_DATA, movieData)
                }
                startActivityForResult(intent, DetailMovieActivity.INTENT_KEY)
                AnimIntent.Builder(requireActivity()).performSlideToLeft()
            }
        }
    }

    private fun implementData(data: MovieResponse) {
        if (isVisible) {
            Logger.debug("cek impelment data ${data.results?.get(0)?.originalTitleMovie}")
            this.movieResponse = data
            shimmer_movie.makeGone()
            getListMovie()?.setData(movieResponse?.results!!)
        }

    }

    private fun getListMovie(): AdapterMovie? = rv_home?.adapter as? AdapterMovie

    companion object {
        const val TAG = "HOME_FRAGMENT"
    }
}