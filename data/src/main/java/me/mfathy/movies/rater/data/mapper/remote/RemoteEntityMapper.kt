package me.mfathy.movies.rater.data.mapper.remote

import me.mfathy.movies.rater.data.mapper.EntityMapper


/**
 * Mapper contract to convert and map remote data entities.
 */
interface RemoteEntityMapper<E, D> : EntityMapper<E, D> {
    override fun mapToEntity(domain: D): E
}