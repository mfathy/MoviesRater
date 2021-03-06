package me.mfathy.movies.rater.data.mapper.data

import me.mfathy.movies.rater.data.mapper.base.DataEntityMapper
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.domain.model.Movie
import javax.inject.Inject

open class DataEntityMapperImpl @Inject constructor() :
    DataEntityMapper<MovieEntity, Movie> {
    override fun mapFromEntity(entity: MovieEntity): Movie = Movie(
        id = entity.id,
        title = entity.title,
        coverUrl = entity.coverUrl,
        description = entity.description,
        rating = entity.rating
    )

    override fun mapToEntity(domain: Movie): MovieEntity = MovieEntity(
        id = domain.id,
        title = domain.title,
        coverUrl = domain.coverUrl,
        description = domain.description,
        rating = domain.rating
    )
}