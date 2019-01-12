package me.mfathy.movies.rater.data.mapper.cache

import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie
import javax.inject.Inject

class CacheEntityMapperImpl @Inject constructor() : CacheEntityMapper<MovieEntity, CachedMovie> {
    override fun mapFromEntity(entity: MovieEntity): CachedMovie = CachedMovie(
        id = entity.id,
        rating = entity.rating,
        description = entity.description,
        coverUrl = entity.coverUrl,
        title = entity.title
    )

    override fun mapToEntity(domain: CachedMovie): MovieEntity = MovieEntity(
        id = domain.id,
        rating = domain.rating,
        description = domain.description,
        coverUrl = domain.coverUrl,
        title = domain.title
    )

}