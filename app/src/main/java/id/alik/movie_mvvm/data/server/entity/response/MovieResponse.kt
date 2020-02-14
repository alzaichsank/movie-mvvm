package id.alik.movie_mvvm.data.server.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MovieResponse(
    @SerializedName("results") var results: List<MovieData>?,
    @SerializedName("page") var page: Int?,
    @SerializedName("total_results") var totalResults: Int?,
    @SerializedName("total_pages") var totalPages: Int?
) : Serializable

@Keep
data class MovieData(
    @SerializedName("id") var idMovie: String?,
    @SerializedName("poster_path") var posterPathMovie: String?,
    @SerializedName("original_title") var originalTitleMovie: String?,
    @SerializedName("overview") var overviewMovie: String?,
    @SerializedName("release_date") var releaseDateMovie: String?,
    @SerializedName("backdrop_path") var backdropPathMovie: String?,
    @SerializedName("vote_average") var voteAverageMovie: String?,
    @SerializedName("original_language") var originalLanguage: String?
) : Serializable