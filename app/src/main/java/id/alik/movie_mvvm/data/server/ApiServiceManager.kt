package id.alik.movie_mvvm.data.server

import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.data.server.entity.response.MovieResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceManager(
    private val apiService: ApiService
){
    fun emitterMovie(
        keyword: String,
        apiKey: String
    ): Observable<MovieResponse> {
        return Observable.create {
            apiService.getMovie(
                keyword,
                apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    if (it.results != null) {
                        it
                    }
                },{
                    Logger.debug("cek error : $it")
                })
        }
    }
}