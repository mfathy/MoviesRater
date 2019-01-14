package me.mfathy.movies.injection

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.schedulers.Schedulers
import me.mfathy.movies.rater.domain.executor.ExecutionSchedulers
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.ui.movies.MoviesActivity

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class TestUiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesExecutionThread(): ExecutionThread = ExecutionSchedulers(
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }


    @ContributesAndroidInjector
    abstract fun contributesMoviesActivity(): MoviesActivity
}