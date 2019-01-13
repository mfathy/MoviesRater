package me.mfathy.movies.rater.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import me.mfathy.movies.rater.domain.model.Movie

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 * Data repository contract to define domain layer interactions with data layer.
 */

interface MoviesRepository {

    /**
     * Returns a flowable observable which will emit a list of Movie otherwise Error
     * @return a flowable which will emit a list of Movie
     */
    fun getMovies(): Flowable<MutableList<Movie>>

    /**
     * Rates a movie.
     * @param movie the movie to be rated.
     * @return Completable observable indicates success of failure.
     */
    fun rateMovie(movie: Movie): Completable

}