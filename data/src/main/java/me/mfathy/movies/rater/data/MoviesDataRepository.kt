package me.mfathy.movies.rater.data

import io.reactivex.Completable
import io.reactivex.Flowable
import me.mfathy.movies.rater.data.mapper.data.DataEntityMapperImpl
import me.mfathy.movies.rater.data.store.MoviesDataStoreFactory
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
    private val factory: MoviesDataStoreFactory,
    private val mapper: DataEntityMapperImpl
) : MoviesRepository {
    override fun getMovies(): Flowable<MutableList<Movie>> {
        return factory.getDataStore(true)
            .areMoviesCached()
            .toFlowable()
            .flatMap { isCached ->
                factory.getDataStore(isCached).getMovies()
                    .flatMap {
                        factory.getDataStore(true).saveMovies(it)
                            .andThen(Flowable.just(it))
                    }
                    .map { movies ->
                        movies.map {
                            mapper.mapFromEntity(it)
                        }.toMutableList()
                    }
            }
    }

    override fun rateMovie(movie: Movie): Completable {
        return factory.getDataStore(true).rateMovie(mapper.mapToEntity(movie))
    }
}