package me.mfathy.movies.rater.data.repository

import io.reactivex.Flowable
import me.mfathy.movies.rater.data.model.MovieEntity

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 */

interface MoviesRemote {

    /**
     * Returns a flowable observable which will emit a list of Movie otherwise Error
     * @return a flowable which will emit a list of Movie
     */
    fun getMovies(): Flowable<MutableList<MovieEntity>>

}