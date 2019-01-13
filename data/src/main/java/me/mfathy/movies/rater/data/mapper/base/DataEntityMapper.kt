package me.mfathy.movies.rater.data.mapper.base

interface DataEntityMapper<E, D> : EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
}