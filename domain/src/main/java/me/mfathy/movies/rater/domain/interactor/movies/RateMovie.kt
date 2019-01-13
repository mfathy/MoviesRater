package me.mfathy.movies.rater.domain.interactor.movies

import io.reactivex.Completable
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.domain.interactor.base.CompletableUseCase
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 * Rate movie use case
 */
open class RateMovie @Inject constructor(
    private val dataRepository: MoviesRepository,
    subscriberThread: ExecutionThread,
    postExecutionThread: ExecutionThread
) : CompletableUseCase<RateMovie.Params>(subscriberThread, postExecutionThread) {
    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return dataRepository.rateMovie(movie = params.movie)
    }


    class Params constructor(val movie: Movie) {
        companion object {
            fun forRateMovie(movie: Movie): Params = Params(movie)
        }
    }
}