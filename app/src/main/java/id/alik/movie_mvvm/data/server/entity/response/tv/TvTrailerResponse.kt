package id.alik.movie_mvvm.data.server.entity.response.tv

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class TvTrailerResponse(
    @SerializedName("results") var results: List<TvTrailerData>?,
    @SerializedName("id") var idTvList: Int?
) : Serializable

@Keep
data class TvTrailerData(
    @SerializedName("id") var idTrailerTv: String?,
    @SerializedName("key") var keyEmbed: String?,
    @SerializedName("name") var nameTrailerTv: String?
) : Serializable