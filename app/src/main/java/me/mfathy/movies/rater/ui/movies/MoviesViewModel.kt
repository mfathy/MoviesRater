package me.mfathy.movies.rater.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber
import me.mfathy.movies.rater.domain.interactor.movies.GetMovies
import me.mfathy.movies.rater.domain.interactor.movies.RateMovie
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.ui.state.Resource
import me.mfathy.movies.rater.ui.state.ResourceState
import javax.inject.Inject

/**
 * Movies view model
 * Handles Get movies use case response
 * Handles Rate a movie use case response
 */
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val rateMovie: RateMovie
) : ViewModel() {

    private val moviesLiveData: MutableLiveData<Resource<MutableList<Movie>>> = MutableLiveData()
    private val rateLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    override fun onCleared() {
        getMovies.dispose()
        rateMovie.dispose()
        super.onCleared()
    }

    fun getMoviesLiveData(): LiveData<Resource<MutableList<Movie>>> {
        return moviesLiveData
    }

    fun getRateLiveData(): MutableLiveData<Resource<Boolean>> {
        return rateLiveData
    }

    fun fetchMovies() {
        moviesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getMovies.execute(GetMoviesObserver())
    }

    fun rateMovie(movie: Movie, isRandom: Boolean = false, randomDelay: Long = 0) {
        rateMovie.execute(RateMovieObserver(), RateMovie.Params.forRateMovie(movie, isRandom, randomDelay))
    }

    inner class RateMovieObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            rateLiveData.postValue(Resource(ResourceState.SUCCESS, true, null))
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
            rateLiveData.postValue(Resource(ResourceState.ERROR, null, error))
        }

    }

    inner class GetMoviesObserver : DisposableSubscriber<MutableList<Movie>>() {
        override fun onComplete() {}

        override fun onNext(movies: MutableList<Movie>?) {
            movies?.sortByDescending { it.rating }
            moviesLiveData.postValue(Resource(ResourceState.SUCCESS, movies, null))
        }

        override fun onError(error: Throwable?) {
            moviesLiveData.postValue(Resource(ResourceState.ERROR, null, error))
        }

    }
}


