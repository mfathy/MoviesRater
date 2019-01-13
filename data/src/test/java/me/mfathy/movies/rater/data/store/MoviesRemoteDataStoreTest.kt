package me.mfathy.movies.rater.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesRemote
import org.junit.Test

/**
 * Created by Mohammed Fathy on 13/01/2019.
 * dev.mfathy@gmail.com
 */
class MoviesRemoteDataStoreTest {
    private val mockMoviesRemote = mock<MoviesRemote>()
    private val dataStore = MoviesRemoteDataStore(mockMoviesRemote)

    @Test
    fun testGetMoviesCallsCacheStore() {
        stubMoviesCacheGetMovies()
        dataStore.getMovies().test()
        verify(mockMoviesRemote).getMovies()
    }

    private fun stubMoviesCacheGetMovies() {
        whenever(mockMoviesRemote.getMovies()).thenReturn(Flowable.just(mutableListOf()))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testRateMovieCallsCacheStore() {
        dataStore.rateMovie(MovieEntity()).test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun testSaveMoviesCallsCacheStore() {
        dataStore.saveMovies(mutableListOf())

    }


    @Test(expected = java.lang.UnsupportedOperationException::class)
    fun testAreMoviesCachedCallsCacheStore() {
        dataStore.areMoviesCached().test()
    }
}