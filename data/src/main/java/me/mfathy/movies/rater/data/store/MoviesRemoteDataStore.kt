package me.mfathy.movies.rater.data.store

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesDataStore
import me.mfathy.movies.rater.data.repository.MoviesRemote
import javax.inject.Inject

open class MoviesRemoteDataStore @Inject constructor(private val moviesRemote: MoviesRemote) : MoviesDataStore {
    override fun getMovies(): Flowable<MutableList<MovieEntity>> {
        return moviesRemote.getMovies()
    }

    override fun rateMovie(movieEntity: MovieEntity): Completable {
        throw UnsupportedOperationException("No supported here...")
    }

    override fun saveMovies(movieEntities: MutableList<MovieEntity>): Completable {
        throw UnsupportedOperationException("No supported here...")
    }

    override fun areMoviesCached(): Single<Boolean> {
        throw UnsupportedOperationException("No supported here...")
    }
}