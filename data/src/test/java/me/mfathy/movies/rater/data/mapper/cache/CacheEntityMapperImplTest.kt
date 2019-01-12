package me.mfathy.movies.rater.data.mapper.cache

import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie
import me.mfathy.movies.rater.data.test.MovieEntityDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
class CacheEntityMapperImplTest {

    private val mapper = CacheEntityMapperImpl()

    @Test
    fun testMapFromEntityMapsData() {
        val entity = MovieEntityDataFactory.makeMovieEntity()
        val movie = mapper.mapFromEntity(entity)

        assertEqualData(entity, movie)
    }

    @Test
    fun testMapToEntityMapsData() {
        val movie = MovieEntityDataFactory.makeCachedMovie()
        val entity = mapper.mapToEntity(movie)

        assertEqualData(entity, movie)
    }

    private fun assertEqualData(entity: MovieEntity, domain: CachedMovie) {
        Assert.assertEquals(entity.id, domain.id)
        Assert.assertEquals(entity.coverUrl, domain.coverUrl)
        Assert.assertEquals(entity.description, domain.description)
        Assert.assertEquals(entity.rating, domain.rating, 0.1)
        Assert.assertEquals(entity.title, domain.title)
    }
}