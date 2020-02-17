package id.alik.movie_mvvm.data.server.entity.response.tv

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class TvResponse(
    @SerializedName("results") var results: List<TvData>?,
    @SerializedName("page") var page: Int?,
    @SerializedName("total_results") var totalResults: Int?,
    @SerializedName("total_pages") var totalPages: Int?
) : Serializable

@Keep
data class TvData(
    @SerializedName("id") var idMovie: String?,
    @SerializedName("poster_path") var posterPathMovie: String?,
    @SerializedName("original_name") var originalNameMovie: String?,
    @SerializedName("overview") var overviewMovie: String?,
    @SerializedName("first_air_date") var firstAirDateMovie: String?,
    @SerializedName("backdrop_path") var backdropPathMovie: String?,
    @SerializedName("vote_average") var voteAverageMovie: String?,
    @SerializedName("original_language") var originalLanguage: String?
) : Serializable