package me.mfathy.movies.rater.domain.model

data class Movie(
    val id: String = "",
    val title: String = "",
    val coverUrl: String = "",
    val description: String = "",
    var rating: Double = 0.0
) : Comparable<Movie> {
    override fun compareTo(other: Movie): Int {
        return rating.compareTo(other.rating)
    }


}
