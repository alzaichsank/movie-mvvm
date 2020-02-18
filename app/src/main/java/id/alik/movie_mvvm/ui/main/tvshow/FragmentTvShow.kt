package id.alik.movie_mvvm.ui.main.tvshow

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
import id.alik.movie_mvvm.common.utils.Constants.TV_TYPE
import id.alik.movie_mvvm.data.server.entity.response.tv.TvResponse
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.entity.response.tv.TvData
import id.alik.movie_mvvm.ui.main.tvshow.adapter.AdapterTv
import kotlinx.android.synthetic.main.fragment_tvshow.*
import id.alik.movie_mvvm.service.ViewModelFactory
import id.alik.movie_mvvm.service.RestRepository
import kotlinx.android.synthetic.main.layout_shimmer_movie.*
import id.alik.movie_mvvm.data.mapper.DataMapper
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_DATA
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_FILTER
import id.alik.movie_mvvm.ui.main.detail.DetailMovieActivity

class FragmentTvShow : Fragment(){
    private lateinit var tvViewModel: TvViewModel
    private var tvResponse : TvResponse? = null
    private var tvData: TvData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvViewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(RestRepository.createApiRepository(),TV_TYPE)
        ).get(
            TvViewModel::class.java
        )
        tvViewModel.getTv()
        initRecycler()

        tvViewModel.tvData.observe(requireActivity(), Observer {
            implementData(it)
            Logger.debug("cek load data ${it.results?.get(0)?.originalNameMovie}")
        })
    }

    private fun initRecycler() {
        rv_tvshow.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = AdapterTv(requireActivity()) {
                tvData = getListTv()?.getDataAt(it)
                val data = DataMapper.transfromTvDataToDetailData(tvData!!)
                val intent = Intent(requireActivity(), DetailMovieActivity::class.java).apply {
                    putExtra(INTENT_EXTRA_DATA, data)
                    putExtra(INTENT_EXTRA_FILTER, TV_TYPE)
                }
                startActivityForResult(intent, DetailMovieActivity.INTENT_KEY)
                AnimIntent.Builder(requireActivity()).performSlideToLeft()
            }
        }
    }

    private fun implementData(data : TvResponse){
        if (isVisible) {
            Logger.debug("cek impelment data ${data.results?.get(0)?.originalNameMovie}")
            this.tvResponse = data
            shimmer_movie.makeGone()
            getListTv()?.setData(tvResponse?.results!!)
        }

    }

    private fun getListTv(): AdapterTv? = rv_tvshow?.adapter as? AdapterTv

    companion object {
        const val TAG = "TVSHOW_FRAGMENT"
    }
}