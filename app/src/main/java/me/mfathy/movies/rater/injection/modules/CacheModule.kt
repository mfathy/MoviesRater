package me.mfathy.movies.rater.injection.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.movies.rater.data.repository.MoviesCache
import me.mfathy.movies.rater.data.store.cache.MoviesCacheImpl
import me.mfathy.movies.rater.data.store.cache.MoviesDatabase

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.mfathy@gmail.com
 *
 * Dagger module to provide cache dependencies.
 */
@Module
abstract class CacheModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): MoviesDatabase {
            return MoviesDatabase.getInstance(application)
        }
    }


    @Binds
    abstract fun bindCacheStore(cache: MoviesCacheImpl): MoviesCache
}