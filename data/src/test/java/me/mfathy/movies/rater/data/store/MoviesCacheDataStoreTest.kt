package me.mfathy.movies.rater.data.store

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesCache
import org.junit.Test

/**
 * Created by Mohammed Fathy on 13/01/2019.
 * dev.mfathy@gmail.com
 */
class MoviesCacheDataStoreTest {

    private val mockMoviesCache = mock<MoviesCache>()
    private val cacheDataStore = MoviesCacheDataStore(mockMoviesCache)

    @Test
    fun testGetMoviesCallsCacheStore() {
        stubMoviesCacheGetMovies()
        cacheDataStore.getMovies().test()
        verify(mockMoviesCache).getMovies()
    }

    private fun stubMoviesCacheGetMovies() {
        whenever(mockMoviesCache.getMovies()).thenReturn(Flowable.just(mutableListOf()))
    }

    @Test
    fun testRateMovieCallsCacheStore() {
        stubMoviesCacheRateMovie()
        cacheDataStore.rateMovie(MovieEntity()).test()

        verify(mockMoviesCache).rateMovie(any())

    }

    private fun stubMoviesCacheRateMovie() {
        whenever(mockMoviesCache.rateMovie(any())).thenReturn(Completable.complete())
    }

    @Test
    fun testSaveMoviesCallsCacheStore() {
        stubMoviesCacheSaveMovie()
        cacheDataStore.saveMovies(mutableListOf())

        verify(mockMoviesCache).saveMovies(any())
    }

    private fun stubMoviesCacheSaveMovie() {
        whenever(mockMoviesCache.saveMovies(any())).thenReturn(Completable.complete())
    }

    @Test
    fun testAreMoviesCachedCallsCacheStore() {
        stubMoviesCacheAreMovieCached()
        cacheDataStore.areMoviesCached().test()

        verify(mockMoviesCache).areMoviesCached()


    }

    private fun stubMoviesCacheAreMovieCached() {
        whenever(mockMoviesCache.areMoviesCached()).thenReturn(Single.just(true))
    }
}