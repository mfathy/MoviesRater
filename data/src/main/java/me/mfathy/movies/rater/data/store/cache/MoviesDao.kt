package me.mfathy.movies.rater.data.store.cache

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
@Dao
abstract class MoviesDao {
    @Query("SELECT * FROM CachedMovies ORDER BY movie_rating DESC")
    abstract fun getCachedMovies(): Flowable<List<CachedMovie>>

    @Query("SELECT COUNT(*) FROM CachedMovies")
    abstract fun getCachedMoviesCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveMovieList(cachedMovies: List<CachedMovie>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateMovieRate(cachedMovie: CachedMovie)
}