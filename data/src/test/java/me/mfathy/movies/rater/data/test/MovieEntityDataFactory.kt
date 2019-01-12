package me.mfathy.movies.rater.data.test

import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie
import me.mfathy.movies.rater.data.test.DataEntityFactory.randomDouble
import me.mfathy.movies.rater.data.test.DataEntityFactory.randomString
import me.mfathy.movies.rater.domain.model.Movie

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object MovieEntityDataFactory {

    fun makeMovieEntity(): MovieEntity = MovieEntity(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomDouble()
    )

    fun makeMovie(): Movie = Movie(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomDouble()
    )

    fun makeCachedMovie(): CachedMovie = CachedMovie(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomDouble()
    )

    fun makeMovieList(count: Int): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(makeMovie())
        }
        return movies
    }

    fun makeMovieEntityList(count: Int): MutableList<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        repeat(count) {
            movies.add(makeMovieEntity())
        }
        return movies
    }

}