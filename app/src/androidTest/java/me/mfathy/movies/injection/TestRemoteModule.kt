package me.mfathy.movies.injection

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import me.mfathy.movies.rater.data.repository.MoviesDataStore

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideRemoteDataStore(): MoviesDataStore {
        return mock()
    }

}