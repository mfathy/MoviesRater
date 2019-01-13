package me.mfathy.movies.rater.injection.modules

import dagger.Binds
import dagger.Module
import me.mfathy.movies.rater.data.MoviesDataRepository
import me.mfathy.movies.rater.domain.repository.MoviesRepository

/**
 * Dagger module to provide data repository dependencies.
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: MoviesDataRepository): MoviesRepository

}