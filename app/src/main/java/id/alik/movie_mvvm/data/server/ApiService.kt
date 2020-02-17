package id.alik.movie_mvvm.data.server

import id.alik.movie_mvvm.data.server.entity.response.movie.MovieResponse
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvTrailerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET(NOWPLAYING)
    fun getMovie(
        @Query("api_key") api_key: String?
    ): Observable<MovieResponse>

    @GET(TVTONAIR)
    fun getTvShow(
        @Query("api_key") api_key: String
    ): Observable<TvResponse>

    @GET
    fun getMovieTrailer(
        @Url url: String,
        @Query("api_key") api_key: String
    ): Observable<MovieTrailerResponse>

    @GET
    fun getTvTrailer(
        @Url url: String,
        @Query("api_key") api_key: String
    ): Observable<TvTrailerResponse>

    companion object {
        private const val SEARCHMOVIE = "3/search/movie"
        private const val SEARCHTV = "3/search/tv"
        private const val NOWPLAYING = "3/movie/now_playing"
        private const val UPCOMING = "3/movie/upcoming"
        private const val TOPRATED = "3/movie/top_rated"
        private const val TVTONAIR = "3/tv/on_the_air"
        private const val MOVIERELEASETODAY = "3/discover/movie"
        const val VIDEOSTRAILER = "/videos"
        const val MOVIETRAILER = "3/movie/"
        const val TVTRAILER = "3/tv/"
    }
}