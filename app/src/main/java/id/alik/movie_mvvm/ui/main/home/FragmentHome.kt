package id.alik.movie_mvvm.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.data.server.entity.response.MovieResponse
import id.alik.movie_mvvm.service.ViewModelFactory
import id.alik.movie_mvvm.service.RestRepository
import id.alik.movie_mvvm.ui.main.home.adapter.AdapterMovie
import kotlinx.android.synthetic.main.fragment_home.*
import id.alik.movie_mvvm.common.utils.Logger

class FragmentHome : Fragment(){

    private lateinit var movieViewModel: MovieViewModel
    private var movieResponse : MovieResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProviders.of(requireActivity(), ViewModelFactory(RestRepository.createApiRepository())).get(
            MovieViewModel::class.java)
        movieViewModel.getMovie()
        initRecyler()

        movieViewModel.movieData.observe(requireActivity(), Observer {
            implementData(it)
            Logger.debug("cek load data ${it.results?.get(0)?.originalTitleMovie}")
        })

    }
    private fun initRecyler() {
        rv_home.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = AdapterMovie(requireActivity()) {

            }
        }
    }

    private fun implementData(data : MovieResponse){
        if (isVisible) {
            Logger.debug("cek impelment data ${data.results?.get(0)?.originalTitleMovie}")
            this.movieResponse = data
            getListMovie()?.setData(movieResponse?.results!!)
        }

    }

    private fun getListMovie(): AdapterMovie? = rv_home?.adapter as? AdapterMovie
    companion object {
        const val TAG = "HOME_FRAGMENT"
    }
}