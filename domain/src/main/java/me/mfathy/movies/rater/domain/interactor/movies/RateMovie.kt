package me.mfathy.movies.rater.domain.interactor.movies

import io.reactivex.Completable
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.domain.interactor.base.CompletableUseCase
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
        return dataRepository.rateMovie(params.movieId, params.rating)
    }


    class Params constructor(
        val movieId: String,
        val rating: Double
    ) {
        companion object {
            fun forRateMovie(movieId: String, rating: Double): Params = Params(movieId, rating)
        }
    }
}