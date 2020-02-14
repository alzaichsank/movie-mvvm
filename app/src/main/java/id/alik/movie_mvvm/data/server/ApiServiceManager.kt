package id.alik.movie_mvvm.data.server

import id.alik.movie_mvvm.data.server.entity.response.MovieResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceManager(
    val apiService: ApiService
){
    fun emitterMovie(
        keyword: String,
        apiKey: String
    ): Observable<MovieResponse> {
        return Observable.create { emitter ->
            apiService.getMovie(
                keyword,
                apiKey
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {

                },{

                })
        }
    }
}