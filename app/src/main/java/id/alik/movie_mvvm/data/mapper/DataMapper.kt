package id.alik.movie_mvvm.data.mapper

import id.alik.movie_mvvm.data.server.entity.response.movie.MovieData
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerData
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerResponse
import id.alik.movie_mvvm.data.server.entity.response.tv.TvData
import id.alik.movie_mvvm.data.server.entity.response.tv.TvTrailerData
import id.alik.movie_mvvm.data.server.entity.response.tv.TvTrailerResponse
import id.alik.movie_mvvm.domain.entity.DetailData
import id.alik.movie_mvvm.domain.entity.TrailerDetailData

object DataMapper {
    fun transfromMovieDataToDetailData(it: MovieData): DetailData {
        return DetailData(
            id = it.idMovie,
            name = it.originalTitleMovie,
            poster = it.posterPathMovie,
            overview = it.overviewMovie,
            release = it.releaseDateMovie,
            backdropPoster = it.backdropPathMovie,
            rating = it.voteAverageMovie,
            language = it.originalLanguage
        )
    }

    fun transfromTvDataToDetailData(it: TvData): DetailData {
        return DetailData(
            id = it.idMovie,
            name = it.originalNameMovie,
            poster = it.posterPathMovie,
            overview = it.overviewMovie,
            release = it.firstAirDateMovie,
            backdropPoster = it.backdropPathMovie,
            rating = it.voteAverageMovie,
            language = it.originalLanguage
        )
    }

    fun transfromMovieTrailerDataToTrailerDetailData(it: MovieTrailerResponse): List<TrailerDetailData> {
        val transformedData = mutableListOf<TrailerDetailData>()
        it.results?.forEach {
            transformedData.add(
                TrailerDetailData(
                    id = it.idTrailerMovie,
                    name = it.nameTrailerMovie,
                    key = it.keyEmbed
                )
            )
        }
        return transformedData

    }

    fun transfromTvTrailerDataToTrailerDetailData(it: TvTrailerResponse): List<TrailerDetailData> {
        val transformedData = mutableListOf<TrailerDetailData>()
        it.results?.forEach {
            transformedData.add(
                TrailerDetailData(
                    id = it.idTrailerTv,
                    name = it.nameTrailerTv,
                    key = it.keyEmbed
                )
            )
        }
        return transformedData
    }
}