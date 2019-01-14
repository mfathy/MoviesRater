package me.mfathy.movies.rater.domain.interactor.base

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.domain.interactor.movies.RateMovie
import java.util.concurrent.TimeUnit

/**
 * A CompletableUseCase is an abstract class which provide a Completable observable to
 * indicate completion or error.
 */
abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: ExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        if (params is RateMovie.Params) {
            val completable = this.buildUseCaseCompletable(params)
                .subscribeOn(postExecutionThread.subscribeScheduler)
                .observeOn(postExecutionThread.observeScheduler)
            if (params.random) {
                val delayCompletable = completable.delay(params.delay, TimeUnit.SECONDS)
                addDisposable(delayCompletable.subscribeWith(observer))
            } else {
                addDisposable(completable.subscribeWith(observer))
            }
        }

    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }


}