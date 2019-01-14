package me.mfathy.movies.rater.domain.executor

import io.reactivex.Scheduler
import javax.inject.Inject

class ExecutionSchedulers @Inject constructor(
    override val subscribeScheduler: Scheduler,
    override val observeScheduler: Scheduler
) : ExecutionThread