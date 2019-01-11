package me.mfathy.movies.rater.domain.executor

import io.reactivex.Scheduler

/**
 * Scheduler thread contract.
 */
interface ExecutionThread {
    val scheduler: Scheduler
}
