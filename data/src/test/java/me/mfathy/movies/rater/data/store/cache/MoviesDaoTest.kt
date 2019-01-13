package me.mfathy.movies.rater.data.store.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import me.mfathy.movies.rater.data.test.MovieEntityDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
@RunWith(RobolectricTestRunner::class)
class MoviesDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        MoviesDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveMovieCompletes() {
        val cachedMovie = MovieEntityDataFactory.makeCachedMovie()
        val testObserver = database.moviesDao().saveMovieList(listOf(cachedMovie)).test()

        testObserver.assertComplete()
    }

    @Test
    fun testGetCachedMoviesReturnsData() {
        val cachedMovie = MovieEntityDataFactory.makeCachedMovie()
        database.moviesDao().saveMovieList(listOf(cachedMovie)).test()

        val testObserver = database.moviesDao().getCachedMovies().test()
        testObserver.assertValue(listOf(cachedMovie))
    }

    @Test
    fun testGetCachedMoviesCountReturnsCount() {
        val cachedMovie = MovieEntityDataFactory.makeCachedMovie()
        database.moviesDao().saveMovieList(listOf(cachedMovie)).test()

        val testObserver = database.moviesDao().getCachedMoviesCount().test()
        testObserver.assertValue(1)
    }

    @Test
    fun testUpdateMovieRateUpdatesRating() {
        val cachedMovie = MovieEntityDataFactory.makeCachedMovie()
        database.moviesDao().saveMovieList(listOf(cachedMovie)).test()

        val updatedMovie = cachedMovie.copy(rating = 9.3)

        database.moviesDao().updateMovieRate(updatedMovie).test()

        val testObserver = database.moviesDao().getCachedMovies().test()
        testObserver.assertValue(listOf(updatedMovie))
    }
}