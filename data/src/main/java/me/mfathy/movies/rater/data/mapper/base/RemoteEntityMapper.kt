package me.mfathy.movies.rater.data.mapper.base


/**
 * Mapper contract to convert and map remote data entities.
 */
interface RemoteEntityMapper<E, D> : EntityMapper<E, D> {
    override fun mapToEntity(domain: D): E
}