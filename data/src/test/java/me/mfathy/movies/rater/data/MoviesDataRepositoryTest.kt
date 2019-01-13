package me.mfathy.movies.rater.data

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import me.mfathy.movies.rater.data.mapper.data.DataEntityMapperImpl
import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.repository.MoviesDataStore
import me.mfathy.movies.rater.data.store.MoviesDataStoreFactory
import me.mfathy.movies.rater.data.test.MovieEntityDataFactory
import me.mfathy.movies.rater.domain.model.Movie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesDataRepositoryTest {

    private val mockFactory = mock<MoviesDataStoreFactory>()
    private val mockMapper = mock<DataEntityMapperImpl>()
    private val mockStore = mock<MoviesDataStore>()
    private val repository = MoviesDataRepository(mockFactory, mockMapper)
    private val mapper = DataEntityMapperImpl()


    @Before
    fun setup() {
        stubFactoryGetDataStore()
    }

    @Test
    fun testGetMoviesCompletes() {
        val entity = MovieEntityDataFactory.makeMovieEntity()
        val movie = mapper.mapFromEntity(entity)
        stubDataMapperFromEntity(movie)
        stubGetCacheStoreAreMoviesCached(Single.just(false))
        stubGetDataStoreGetMovies(Flowable.just(mutableListOf(entity)))
        stubGetDataStoreSaveMovies(Completable.complete())
        val testObserver = repository.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetMoviesReturnData() {
        val entity = MovieEntityDataFactory.makeMovieEntity()
        val movie = mapper.mapFromEntity(entity)
        stubDataMapperFromEntity(movie)
        stubGetCacheStoreAreMoviesCached(Single.just(false))
        stubGetDataStoreGetMovies(Flowable.just(mutableListOf(entity)))
        stubGetDataStoreSaveMovies(Completable.complete())
        val testObserver = repository.getMovies().test()
        testObserver.assertValue(mutableListOf(movie))
    }

    @Test
    fun testGetMoviesReturnCallStore() {
        val entity = MovieEntityDataFactory.makeMovieEntity()
        val movie = mapper.mapFromEntity(entity)
        stubDataMapperFromEntity(movie)
        stubGetCacheStoreAreMoviesCached(Single.just(false))
        stubGetDataStoreGetMovies(Flowable.just(mutableListOf(entity)))
        stubGetDataStoreSaveMovies(Completable.complete())
        repository.getMovies().test()

        verify(mockFactory, times(3)).getDataStore(any())
        verify(mockMapper).mapFromEntity(any())
    }


    @Test
    fun testRateMovieCompletes() {
        val movie = MovieEntityDataFactory.makeMovie()
        stubFactoryGetDataStore()
        stubDataStoreRateMovie()
        stubDataMapperToEntity(mapper.mapToEntity(movie))
        val testObserver = repository.rateMovie(movie).test()
        testObserver.assertComplete()
    }

    @Test
    fun testRateMovieCallStore() {
        val movie = MovieEntityDataFactory.makeMovie()
        val entity = mapper.mapToEntity(movie)
        stubFactoryGetDataStore()
        stubDataStoreRateMovie()
        stubDataMapperToEntity(entity)
        repository.rateMovie(movie).test()

        verify(mockFactory, times(1)).getDataStore(any())
        verify(mockMapper).mapToEntity(any())
    }

    private fun stubDataStoreRateMovie() {
        whenever(mockStore.rateMovie(any())).thenReturn(Completable.complete())
    }

    private fun stubDataMapperFromEntity(movie: Movie) {
        whenever(mockMapper.mapFromEntity(any())).thenReturn(movie)
    }

    private fun stubDataMapperToEntity(entity: MovieEntity) {
        whenever(mockMapper.mapToEntity(any())).thenReturn(entity)
    }

    private fun stubGetDataStoreSaveMovies(complete: Completable?) {
        whenever(mockStore.saveMovies(any())).thenReturn(complete)
    }

    private fun stubGetDataStoreGetMovies(flowable: Flowable<MutableList<MovieEntity>>?) {
        whenever(mockStore.getMovies()).thenReturn(flowable)
    }

    private fun stubGetCacheStoreAreMoviesCached(single: Single<Boolean>?) {
        whenever(mockStore.areMoviesCached()).thenReturn(single)
    }

    private fun stubFactoryGetDataStore() {
        whenever(mockFactory.getDataStore(any())).thenReturn(mockStore)
    }

}