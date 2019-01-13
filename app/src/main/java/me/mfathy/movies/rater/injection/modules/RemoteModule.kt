package me.mfathy.movies.rater.injection.modules

import dagger.Binds
import dagger.Module
import me.mfathy.movies.rater.data.repository.MoviesRemote
import me.mfathy.movies.rater.data.store.remote.MoviesRemoteImpl

/**
 * Created by Mohammed Fathy on 08/12/2018.
 * dev.mfathy@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */
@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteStore(remote: MoviesRemoteImpl): MoviesRemote

}