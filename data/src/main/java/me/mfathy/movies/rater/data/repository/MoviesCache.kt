package me.mfathy.movies.rater.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.model.MovieEntity

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 */

interface MoviesCache {

    /**
     * Returns a flowable observable which will emit a list of Movie otherwise Error
     * @return a flowable which will emit a list of Movie
     */
    fun getMovies(): Flowable<MutableList<MovieEntity>>

    /**
     * Rates a movie.
     * @param movieEntity the movie to be rated.
     * @return Completable observable indicates success of failure.
     */
    fun rateMovie(movieEntity: MovieEntity): Completable

    /**
     * Saves a list of MovieEntity.
     * @param movieEntities a list of MovieEntity.
     * @return Completable observable indicates success of failure.
     */
    fun saveMovies(movieEntities: MutableList<MovieEntity>): Completable

    /**
     * Check that there are movies cached in the local data store.
     * @return Completable observable indicates success of failure.
     */
    fun areMoviesCached(): Single<Boolean>

}