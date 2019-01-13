package me.mfathy.movies.rater.data.store.cache

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
@Dao
abstract class MoviesDao {
    @Query("SELECT * FROM movies")
    @JvmSuppressWildcards
    abstract fun getCachedMovies(): Flowable<List<CachedMovie>>

    @Query("SELECT COUNT(*) FROM movies")
    @JvmSuppressWildcards
    abstract fun getCachedMoviesCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun saveMovieList(cachedMovies: List<CachedMovie>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateMovieRate(cachedMovie: CachedMovie): Completable
}