package me.mfathy.movies.rater.domain.test

import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.domain.test.DataFactory.randomDouble
import me.mfathy.movies.rater.domain.test.DataFactory.randomString

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object MovieDataFactory {

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