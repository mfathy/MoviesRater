package me.mfathy.movies.rater.domain.interactor.movies

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.domain.repository.MoviesRepository
import me.mfathy.movies.rater.domain.test.MovieDataFactory.makeMovieEntityList
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
class GetMoviesTest {
    private lateinit var mGetMovies: GetMovies

    @Mock
    lateinit var mockRepository: MoviesRepository

    @Mock
    lateinit var mockSubThread: ExecutionThread

    @Before
    fun setUp() {
        mGetMovies = GetMovies(mockRepository, mockSubThread, mockSubThread)
    }

    @Test
    fun testGetMoviesCompletes() {
        stubMoviesRepositoryGetMovies(Flowable.just(makeMovieEntityList(1)))
        val testSubscriber = mGetMovies.buildUseCaseObservable().test()
        testSubscriber.assertComplete()
    }

    @Test
    fun testGetMoviesCallRepository() {
        stubMoviesRepositoryGetMovies(Flowable.just(makeMovieEntityList(2)))
        mGetMovies.buildUseCaseObservable().test()

        verify(mockRepository).getMovies()
    }

    private fun stubMoviesRepositoryGetMovies(observable: Flowable<MutableList<Movie>>) {
        whenever(mockRepository.getMovies()).thenReturn(observable)
    }
}