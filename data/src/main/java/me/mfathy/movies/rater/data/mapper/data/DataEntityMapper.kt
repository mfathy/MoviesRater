package me.mfathy.movies.rater.data.mapper.data

import me.mfathy.movies.rater.data.mapper.EntityMapper

interface DataEntityMapper<E, D> : EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
}