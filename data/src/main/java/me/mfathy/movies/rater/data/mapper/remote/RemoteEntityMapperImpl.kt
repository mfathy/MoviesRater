package me.mfathy.movies.rater.data.mapper.remote

import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.store.remote.model.RemoteMovie
import javax.inject.Inject

class RemoteEntityMapperImpl @Inject constructor() : RemoteEntityMapper<MovieEntity, RemoteMovie> {
    override fun mapToEntity(domain: RemoteMovie): MovieEntity = MovieEntity(
        id = domain.id,
        rating = domain.rating,
        description = domain.description,
        coverUrl = domain.coverUrl,
        title = domain.title
    )
}