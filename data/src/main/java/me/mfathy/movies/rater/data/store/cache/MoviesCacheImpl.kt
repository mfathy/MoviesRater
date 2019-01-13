package me.mfathy.movies.rater.data.store.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.mapper.cache.CacheEntityMapperImpl
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesCache
import javax.inject.Inject

open class MoviesCacheImpl @Inject constructor(
    private val database: MoviesDatabase,
    private val mapper: CacheEntityMapperImpl
) : MoviesCache {
    override fun rateMovie(movieEntity: MovieEntity): Completable {
        return Completable.defer {
            database.moviesDao().updateMovieRate(mapper.mapFromEntity(movieEntity))
            Completable.complete()
        }
    }

    override fun saveMovies(movieEntities: MutableList<MovieEntity>): Completable {
        return Completable.defer {
            val cachedMovies = movieEntities.map {
                mapper.mapFromEntity(it)
            }
            database.moviesDao().saveMovieList(cachedMovies)
            Completable.complete()
        }
    }

    override fun areMoviesCached(): Single<Boolean> {
        return database.moviesDao().getCachedMoviesCount()
            .map { it > 0 }
    }

    override fun getMovies(): Flowable<MutableList<MovieEntity>> {
        return database.moviesDao().getCachedMovies().map { movies ->
            movies.map { mapper.mapToEntity(it) }.toMutableList()
        }
    }
}