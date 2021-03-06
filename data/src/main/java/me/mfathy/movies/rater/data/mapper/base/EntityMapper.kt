package me.mfathy.movies.rater.data.mapper.base


/**
 * Mapper contract to convert and map data entities.
 */
interface EntityMapper<E, D> {
    fun mapToEntity(domain: D): E
}