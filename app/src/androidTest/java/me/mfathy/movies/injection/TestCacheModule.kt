package me.mfathy.movies.injection

import android.app.Application
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import me.mfathy.movies.rater.data.repository.MoviesDataStore
import me.mfathy.movies.rater.data.store.cache.MoviesDatabase

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): MoviesDatabase {
        return MoviesDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideCacheStore(): MoviesDataStore {
        return mock()
    }

}