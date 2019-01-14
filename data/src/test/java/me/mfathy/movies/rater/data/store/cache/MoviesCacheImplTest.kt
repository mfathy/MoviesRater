package me.mfathy.movies.rater.data.store.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import me.mfathy.movies.rater.data.mapper.cache.CacheEntityMapperImpl
import me.mfathy.movies.rater.data.test.MovieEntityDataFactory
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
class MoviesCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        MoviesDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
    private val mapper = CacheEntityMapperImpl()
    private val store = MoviesCacheImpl(database, mapper)


    @Test
    fun testRateMovieCompletes() {
        val entities = MovieEntityDataFactory.makeMovieEntityList(1)
        store.saveMovies(entities).test()
        val testObserver = store.rateMovie(entities[0]).test()

        testObserver.assertComplete()
    }

    @Test
    fun testSaveMoviesCompletes() {
        val entities = MovieEntityDataFactory.makeMovieEntityList(1)
        val testObserver = store.saveMovies(entities).test()

        testObserver.assertComplete()
    }

    @Test
    fun testAreMoviesCachedCompletes() {
        val entities = MovieEntityDataFactory.makeMovieEntityList(1)
        store.saveMovies(entities).test()
        val testObserver = store.areMoviesCached().test()

        testObserver.assertComplete()
    }

    @Test
    fun testGetMoviesReturnsData() {
        val entities = MovieEntityDataFactory.makeMovieEntityList(1)
        store.saveMovies(entities).test()


        val testObserver = store.getMovies().test()
        testObserver.assertValue(entities)
    }
}