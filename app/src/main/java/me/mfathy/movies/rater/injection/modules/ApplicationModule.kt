package me.mfathy.movies.rater.injection.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import me.mfathy.movies.rater.MoviesApplication


/**
 * Dagger application module to provide app context.
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: MoviesApplication): Context

}
