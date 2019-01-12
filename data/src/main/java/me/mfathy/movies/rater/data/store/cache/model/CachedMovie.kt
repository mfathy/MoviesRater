package me.mfathy.movies.rater.data.store.cache.model

data class CachedMovie(
    val id: String = "",
    val title: String = "",
    val coverUrl: String = "",
    val description: String = "",
    var rating: Double = 0.0
)
