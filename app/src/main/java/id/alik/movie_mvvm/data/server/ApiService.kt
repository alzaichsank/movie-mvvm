package id.alik.movie_mvvm.data.server

import id.alik.movie_mvvm.data.server.entity.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(POPULAR)
    fun getMovie(
        @Query("api_key") api_key: String?
    ): Observable<MovieResponse>

    companion object {
        private const val MOVIE = "3/search/movie"
        private const val POPULAR = "3/movie/popular"
        private const val UPCOMING = "3/movie/upcoming"
        private const val TOPRATED = "3/movie/top_rated"
        private const val TRAILER = "3/movie/"
    }
}