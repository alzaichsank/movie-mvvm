package id.alik.movie_mvvm.domain.entity

import java.io.Serializable

data class DetailData(
    var id: String?,
    var poster: String?,
    var name: String?,
    var overview: String?,
    var release: String?,
    var backdropPoster: String?,
    var rating: String?,
    var language: String?
) : Serializable