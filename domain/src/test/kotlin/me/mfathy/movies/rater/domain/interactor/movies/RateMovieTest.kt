package me.mfathy.movies.rater.domain.interactor.movies

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.domain.repository.MoviesRepository
import me.mfathy.movies.rater.domain.test.MovieDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class RateMovieTest {
    private lateinit var mRateMovie: RateMovie

    @Mock
    lateinit var mockRepository: MoviesRepository

    @Mock
    lateinit var mockSubThread: ExecutionThread

    @Before
    fun setUp() {
        mRateMovie = RateMovie(mockRepository, mockSubThread, mockSubThread)
    }

    @Test
    fun testRateMovieCompletes() {
        val movie = MovieDataFactory.makeMovie()
        stubMoviesRepositoryRateMovie(Completable.complete())
        val params = RateMovie.Params.forRateMovie(movie)
        val testSubscriber = mRateMovie.buildUseCaseCompletable(params).test()
        testSubscriber.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGetMoviesThrowsException() {
        mRateMovie.buildUseCaseCompletable().test()
    }

    @Test
    fun testGetMoviesCallRepository() {
        val movie = MovieDataFactory.makeMovie()
        stubMoviesRepositoryRateMovie(Completable.complete())
        val params = RateMovie.Params.forRateMovie(movie)
        mRateMovie.buildUseCaseCompletable(params).test()

        verify(mockRepository).rateMovie(any())
    }

    private fun stubMoviesRepositoryRateMovie(complete: Completable?) {
        whenever(mockRepository.rateMovie(any())).thenReturn(complete)
    }
}