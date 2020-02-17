package id.alik.movie_mvvm.data.server.entity.response.movie

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MovieTrailerResponse(
    @SerializedName("results") var results: List<MovieTrailerData>?,
    @SerializedName("id") var idMovieList: Int?
) : Serializable

@Keep
data class MovieTrailerData(
    @SerializedName("id") var idTrailerMovie: String?,
    @SerializedName("key") var keyEmbed: String?,
    @SerializedName("name") var nameTrailerMovie: String?
) : Serializable