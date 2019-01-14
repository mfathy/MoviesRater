package me.mfathy.movies.rater.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subscribers.DisposableSubscriber
import me.mfathy.movies.rater.domain.interactor.movies.GetMovies
import me.mfathy.movies.rater.domain.interactor.movies.RateMovie
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.test.AppMovieDataFactory
import me.mfathy.movies.rater.ui.state.ResourceState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

/**
 * Created by Mohammed Fathy on 14/01/2019.
 * dev.mfathy@gmail.com
 *
 * Unit test for MoviesViewModel
 */
@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var mockGetMovies: GetMovies = mock()
    private var mockRateMovie: RateMovie = mock()
    private var moviesViewModel = MoviesViewModel(mockGetMovies, mockRateMovie)

    @Captor
    val captor = argumentCaptor<DisposableSubscriber<MutableList<Movie>>>()

    @Test
    fun testFetchMoviesExecutesUseCase() {
        moviesViewModel.fetchMovies()

        verify(mockGetMovies, times(1)).execute(any())
    }

    @Test
    fun testFetchMoviesReturnsSuccess() {
        val movies = AppMovieDataFactory.makeMovieEntityList(2)

        moviesViewModel.fetchMovies()

        verify(mockGetMovies).execute(captor.capture())
        captor.firstValue.onNext(movies)

        assertEquals(ResourceState.SUCCESS, moviesViewModel.getMoviesLiveData().value?.status)
    }

    @Test
    fun testFetchMoviesReturnsData() {
        val movies = AppMovieDataFactory.makeMovieEntityList(2)

        moviesViewModel.fetchMovies()

        verify(mockGetMovies).execute(captor.capture())
        captor.firstValue.onNext(movies)

        assertEquals(movies, moviesViewModel.getMoviesLiveData().value?.data)
    }

    @Test
    fun testFetchMoviesReturnsError() {

        moviesViewModel.fetchMovies()

        verify(mockGetMovies).execute(captor.capture())
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, moviesViewModel.getMoviesLiveData().value?.status)
    }

    @Test
    fun testRateMovieExecutesUseCase() {
        moviesViewModel.rateMovie(AppMovieDataFactory.makeMovie())

        verify(mockRateMovie, times(1)).execute(any(), any())
    }

}