package me.mfathy.movies.rater.data.mapper.cache

import me.mfathy.movies.rater.data.mapper.EntityMapper

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 *
 * Mapper contract to convert and map cache data entities.
 */
interface CacheEntityMapper<E, D> : EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
}