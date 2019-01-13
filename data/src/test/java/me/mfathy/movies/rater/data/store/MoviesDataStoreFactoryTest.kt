package me.mfathy.movies.rater.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
class MoviesDataStoreFactoryTest {


    private val mockCacheStore = mock<MoviesCacheDataStore>()
    private val mockRemoteStore = mock<MoviesRemoteDataStore>()
    private val factory = MoviesDataStoreFactory(
        mockCacheStore,
        mockRemoteStore
    )

    @Test
    fun testGetDataStoreReturnsRemoteSourceWhenNoCacheExists() {
        assert(factory.getDataStore(false) is MoviesRemoteDataStore)
    }

    @Test
    fun testGetCacheDataStoreReturnsCacheDataStore() {
        assert(factory.getCacheDataStore() is MoviesCacheDataStore)
    }

    @Test
    fun testGetRemoteDataStoreReturnsRemoteDataStore() {
        assert(factory.getRemoteDataStore() is MoviesRemoteDataStore)
    }
}