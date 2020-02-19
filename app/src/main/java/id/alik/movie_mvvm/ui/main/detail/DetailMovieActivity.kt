package id.alik.movie_mvvm.ui.main.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.extension.makeGone
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_DATA
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_FILTER
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TRAILER_TYPE
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TYPE
import id.alik.movie_mvvm.common.utils.Constants.TV_TRAILER_TYPE
import id.alik.movie_mvvm.common.utils.Constants.TV_TYPE
import id.alik.movie_mvvm.common.utils.Constants.URL_POSTER
import id.alik.movie_mvvm.common.utils.Constants.YOUTUBE_URL
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.mapper.DataMapper
import id.alik.movie_mvvm.domain.entity.DetailData
import id.alik.movie_mvvm.domain.entity.TrailerDetailData
import id.alik.movie_mvvm.service.RestRepository
import id.alik.movie_mvvm.service.ViewModelFactory
import id.alik.movie_mvvm.ui.main.detail.adapter.AdapterTrailer
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_shimmer_trailer.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var movieTrailerViewModel: DetailMovieViewModel
    private var trailerDetailData: List<TrailerDetailData>? = null
    private var detailData: DetailData? = null
    private var trailerlData: TrailerDetailData? = null
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val extras = intent.extras
        when {
            extras != null -> {
                detailData = extras.getSerializable(INTENT_EXTRA_DATA) as DetailData
                type = extras.getString(INTENT_EXTRA_FILTER)
                Glide.with(this)
                    .load(
                        StringBuilder().append(URL_POSTER)
                            .append(detailData?.backdropPoster).toString()
                    )
                    .fitCenter()
                    .error(R.drawable.no_images)
                    .placeholder(R.drawable.no_images)
                    .into(iv_banner)
                detailData?.name?.let { title = it }
                detailData?.overview.let { tv_description.text = it }
                detailData?.release?.let { tv_year_release.text = it }
                detailData?.rating?.let {
                    tv_rating.text = "$it${getString(R.string.max_rating)}"
                }
                initRecycler()
                when (type) {
                    MOVIE_TYPE -> {
                        movieTrailerViewModel = ViewModelProviders.of(
                            this,
                            ViewModelFactory(
                                RestRepository.createApiRepository(),
                                MOVIE_TRAILER_TYPE
                            )
                        ).get(
                            DetailMovieViewModel::class.java
                        )
                        detailData?.id?.let { movieTrailerViewModel.setIdMovie(it) }
                        movieTrailerViewModel.getMovieTrailer()

                        movieTrailerViewModel.movieTrailerData.observe(this, Observer {
                            val dataMovie =
                                DataMapper.transfromMovieTrailerDataToTrailerDetailData(it)
                            implementData(dataMovie)
                            Logger.debug("cek load data ${it.results?.get(0)?.nameTrailerMovie}")
                        })
                    }
                    TV_TYPE -> {
                        movieTrailerViewModel = ViewModelProviders.of(
                            this,
                            ViewModelFactory(RestRepository.createApiRepository(), TV_TRAILER_TYPE)
                        ).get(
                            DetailMovieViewModel::class.java
                        )
                        detailData?.id?.let { movieTrailerViewModel.setIdMovie(it) }
                        movieTrailerViewModel.getTvTrailer()

                        movieTrailerViewModel.tvTrailerData.observe(this, Observer {
                            val dataMovie = DataMapper.transfromTvTrailerDataToTrailerDetailData(it)
                            implementData(dataMovie)
                            Logger.debug("cek load data ${it.results?.get(0)?.nameTrailerTv}")
                        })

                    }
                }

            }
        }

    }

    private fun initRecycler() {
        rv_trailer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AdapterTrailer(context) {
                trailerlData = getListTrailer()?.getDataAt(it)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_URL + trailerlData?.key))
                intent.putExtra("name", trailerlData?.name)
                startActivity(intent)
            }
        }
    }

    private fun implementData(data: List<TrailerDetailData>) {
        Logger.debug("cek impelment data ${data[0].name}")
        this.trailerDetailData = data
        shimmer_trailer.makeGone()
        getListTrailer()?.setData(trailerDetailData!!.toMutableList())
    }

    private fun getListTrailer(): AdapterTrailer? =
        rv_trailer?.adapter as? AdapterTrailer

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }

    companion object {
        const val INTENT_KEY = 123
    }
}
