package me.mfathy.movies.rater.test.factory


import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.test.factory.AppDataFactory.randomDouble
import me.mfathy.movies.rater.test.factory.AppDataFactory.randomString

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object AppMovieDataFactory {

    fun makeMovie(): Movie = Movie(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomDouble()
    )

    fun makeMovieEntityList(count: Int): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(makeMovie())
        }
        return movies
    }

}