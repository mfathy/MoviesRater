package me.mfathy.movies.rater.injection.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.mfathy.movies.rater.MoviesActivity
import me.mfathy.movies.rater.domain.executor.ExecutionSchedulers
import me.mfathy.movies.rater.domain.executor.ExecutionThread

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(executionScheduler: ExecutionSchedulers): ExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesMoviesActivity(): MoviesActivity
}