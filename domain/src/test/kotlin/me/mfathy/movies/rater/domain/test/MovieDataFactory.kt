package me.mfathy.movies.rater.domain.test

import me.mfathy.movies.rater.domain.model.MovieEntity
import me.mfathy.movies.rater.domain.test.DataFactory.randomDouble
import me.mfathy.movies.rater.domain.test.DataFactory.randomString

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object MovieDataFactory {

    fun makeMovieEntity(): MovieEntity = MovieEntity(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomDouble()
    )

    fun makeMovieEntityList(count: Int): MutableList<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        repeat(count) {
            movies.add(makeMovieEntity())
        }
        return movies
    }

}