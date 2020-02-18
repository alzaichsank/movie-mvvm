package id.alik.movie_mvvm.ui.main.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.extension.makeGone
import id.alik.movie_mvvm.common.utils.Constants.INTENT_EXTRA_DATA
import id.alik.movie_mvvm.common.utils.Constants.MOVIE_TRAILER_TYPE
import id.alik.movie_mvvm.common.utils.Constants.URL_POSTER
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieData
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerResponse
import id.alik.movie_mvvm.service.RestRepository
import id.alik.movie_mvvm.service.ViewModelFactory
import id.alik.movie_mvvm.ui.main.home.detail.adapter.AdapterMovieTrailer
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_shimmer_trailer.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var movieTrailerViewModel: DetailMovieViewModel
    private var movieTrailerResponse: MovieTrailerResponse? = null
    private var movieData: MovieData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val extras = intent.extras
        when {
            extras != null -> {
                movieData = extras.getSerializable(INTENT_EXTRA_DATA) as MovieData
                Glide.with(this)
                    .load(
                        StringBuilder().append(URL_POSTER)
                            .append(movieData?.backdropPathMovie).toString()
                    )
                    .error(R.drawable.no_images)
                    .placeholder(R.drawable.no_images)
                    .into(iv_banner)
                movieData?.originalTitleMovie?.let { title = it }
                movieData?.overviewMovie.let { tv_description.text = it }
                movieData?.releaseDateMovie?.let { tv_year_release.text = it }
                movieData?.voteAverageMovie?.let {
                    tv_rating.text = "$it${getString(R.string.max_rating)}"
                }
                movieTrailerViewModel = ViewModelProviders.of(
                    this,
                    ViewModelFactory(RestRepository.createApiRepository(), MOVIE_TRAILER_TYPE)
                ).get(
                    DetailMovieViewModel::class.java
                )
                movieData?.idMovie?.let { movieTrailerViewModel.setIdMovie(it) }
                movieTrailerViewModel.getMovieTrailer()
                initRecycler()

                movieTrailerViewModel.movieTrailerData.observe(this, Observer {
                    implementData(it)
                    Logger.debug("cek load data ${it.results?.get(0)?.nameTrailerMovie}")
                })
            }
        }

    }

    private fun initRecycler() {
        rv_trailer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AdapterMovieTrailer(context) {

            }
        }
    }

    private fun implementData(data: MovieTrailerResponse) {
        Logger.debug("cek impelment data ${data.results?.get(0)?.nameTrailerMovie}")
        this.movieTrailerResponse = data
        shimmer_trailer.makeGone()
        getListMovieTrailer()?.setData(movieTrailerResponse?.results!!)
    }

    private fun getListMovieTrailer(): AdapterMovieTrailer? =
        rv_trailer?.adapter as? AdapterMovieTrailer

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
