package me.mfathy.movies.rater.data.store

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesCache
import me.mfathy.movies.rater.data.repository.MoviesDataStore
import javax.inject.Inject

open class MoviesCacheDataStore @Inject constructor(private val moviesCache: MoviesCache) : MoviesDataStore {
    override fun getMovies(): Flowable<MutableList<MovieEntity>> {
        return moviesCache.getMovies()
    }

    override fun rateMovie(movieEntity: MovieEntity): Completable {
        return moviesCache.rateMovie(movieEntity)
    }

    override fun saveMovies(movieEntities: MutableList<MovieEntity>): Completable {
        return moviesCache.saveMovies(movieEntities)
    }

    override fun areMoviesCached(): Single<Boolean> {
        return moviesCache.areMoviesCached()
    }
}