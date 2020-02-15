package id.alik.movie_mvvm.ui.main.tvshow

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

class FragmentTvShow : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val TAG = "TVSHOW_FRAGMENT"
    }
}