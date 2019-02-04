package com.vladislawfox.movie.cinema.data.mapper

import com.vladislawfox.base.domain.mapper.Mapper
import com.vladislawfox.movie.cinema.data.model.MovieEntity
import com.vladislawfox.movie.cinema.domain.model.Movie
import javax.inject.Inject

/**
 * Created by vladislawfox on 2/4/19.
 */
class MovieEntityMapper @Inject constructor() : Mapper<MovieEntity, Movie>() {
    override fun reverse(to: Movie): MovieEntity = to.run {
        MovieEntity(
            overview,
            originalLanguage,
            originalTitle,
            video,
            title,
            genreIds,
            posterPath,
            backdropPath,
            releaseDate,
            popularity,
            voteAverage,
            id,
            adult,
            voteCount
        )
    }

    override fun map(from: MovieEntity): Movie = from.run {
      Movie(
          overview,
          originalLanguage,
          originalTitle,
          video,
          title,
          genreIds,
          posterPath,
          backdropPath,
          releaseDate,
          popularity,
          voteAverage,
          id,
          adult,
          voteCount
      )
    }
}