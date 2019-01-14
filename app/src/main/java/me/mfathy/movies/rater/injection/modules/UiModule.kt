package me.mfathy.movies.rater.injection.modules

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.mfathy.movies.rater.domain.executor.ExecutionSchedulers
import me.mfathy.movies.rater.domain.executor.ExecutionThread
import me.mfathy.movies.rater.ui.movies.MoviesActivity

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesExecutionThread(): ExecutionThread = ExecutionSchedulers(
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }


    @ContributesAndroidInjector
    abstract fun contributesMoviesActivity(): MoviesActivity
}