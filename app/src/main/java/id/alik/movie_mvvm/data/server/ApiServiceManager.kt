package id.alik.movie_mvvm.data.server

import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvTrailerResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceManager(
    private val apiService: ApiService
) {
    fun emitterMovie(
        apiKey: String
    ): Observable<MovieResponse> {
        return Observable.create { emitter ->
            apiService.getMovie(
                apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.results != null) {
                        emitter.onNext(it)
                    }
                }, {
                    emitter.onError(it)
                    Logger.debug("cek error : $it")
                })
        }
    }

    fun emitterTv(
        apiKey: String
    ): Observable<TvResponse> {
        return Observable.create { emitter ->
            apiService.getTvShow(
                apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.results != null) {
                        emitter.onNext(it)
                    }
                }, {
                    Logger.debug("cek error : $it")
                })
        }
    }

    fun emitterMovieTrailer(
        idFilm: String,
        apiKey: String
    ): Observable<MovieTrailerResponse> {
        return Observable.create { emitter ->
            apiService.getMovieTrailer(
                ApiService.MOVIETRAILER + idFilm + ApiService.VIDEOSTRAILER, apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.results != null) {
                        if(it.results?.size ?:0 > 0){
                            emitter.onNext(it)
                        }
                    }
                }, {
                    Logger.debug("cek error : $it")
                })
        }
    }

    fun emitterTvTrailer(
        idFilm: String,
        apiKey: String
    ): Observable<TvTrailerResponse> {
        return Observable.create { emitter ->
            apiService.getTvTrailer(
                ApiService.TVTRAILER + idFilm + ApiService.VIDEOSTRAILER, apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.results?.size ?:0 > 0){
                        emitter.onNext(it)
                    }
                }, {
                    Logger.debug("cek error : $it")
                })
        }
    }
}