package me.mfathy.movies.rater.domain.interactor.base

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber
import me.mfathy.movies.rater.domain.executor.ExecutionThread

/**
 * FlowableUseCase is is an abstract class which provide a Flowable observable to
 * emit required data or error.
 *
 * This observable support backpressure.
 */
abstract class FlowableUseCase<T> constructor(
    private val postExecutionThread: ExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(): Flowable<T>

    open fun execute(observer: DisposableSubscriber<T>) {
        val single = this.buildUseCaseObservable()
            .subscribeOn(postExecutionThread.subscribeScheduler)
            .observeOn(postExecutionThread.observeScheduler)
        addDisposable(single.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}