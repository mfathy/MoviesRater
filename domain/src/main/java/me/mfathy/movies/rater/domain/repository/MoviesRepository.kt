package me.mfathy.movies.rater.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import me.mfathy.movies.rater.domain.model.MovieEntity

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 * Data repository contract to define domain layer interactions with data layer.
 */

interface MoviesRepository {

    /**
     * Returns a flowable observable which will emit a list of MovieEntity otherwise Error
     * @return a flowable which will emit a list of MovieEntity
     */
    fun getMovies(): Flowable<MutableList<MovieEntity>>

    /**
     * Rates a movie.
     * @param movieId the id of the movie to be rated.
     * @param rating value of the rating of the specified movie.
     * @return Completable observable indicates success of failure.
     */
    fun rateMovie(movieId: String, rating: Double): Completable

}