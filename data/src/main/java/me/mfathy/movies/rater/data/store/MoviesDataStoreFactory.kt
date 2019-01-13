package me.mfathy.movies.rater.data.store

import me.mfathy.movies.rater.data.repository.MoviesDataStore
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 *
 */

open class MoviesDataStoreFactory @Inject constructor(
    private val cacheDataStore: MoviesCacheDataStore,
    private val remoteDataStore: MoviesRemoteDataStore
) {
    open fun getDataStore(isCached: Boolean): MoviesDataStore {
        return if (isCached) {
            cacheDataStore
        } else {
            remoteDataStore
        }
    }

    open fun getCacheDataStore(): MoviesDataStore {
        return cacheDataStore
    }

    open fun getRemoteDataStore(): MoviesDataStore {
        return remoteDataStore
    }
}