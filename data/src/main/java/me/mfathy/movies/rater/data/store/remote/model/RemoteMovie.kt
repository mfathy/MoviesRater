package me.mfathy.movies.rater.data.store.remote.model

data class RemoteMovie(
    val id: String = "",
    val title: String = "",
    val coverUrl: String = "",
    val description: String = "",
    var rating: Double = 0.0
)
