package me.mfathy.movies.rater.data.mapper

import me.mfathy.movies.rater.data.model.MovieEntity
import me.mfathy.movies.rater.data.test.MovieEntityDataFactory
import me.mfathy.movies.rater.domain.model.Movie
import org.junit.Assert
import org.junit.Test

/**
 * Created by Mohammed Fathy on 12/01/2019.
 * dev.mfathy@gmail.com
 */
class DataEntityMapperImplTest {

    private val mapper = DataEntityMapperImpl()

    @Test
    fun testMapFromEntityMapsData() {
        val entity = MovieEntityDataFactory.makeMovieEntity()
        val movie = mapper.mapFromEntity(entity)

        assertEqualData(entity, movie)
    }

    @Test
    fun testMapToEntityMapsData() {
        val movie = MovieEntityDataFactory.makeMovie()
        val entity = mapper.mapToEntity(movie)

        assertEqualData(entity, movie)
    }

    private fun assertEqualData(entity: MovieEntity, domain: Movie) {
        Assert.assertEquals(entity.id, domain.id)
        Assert.assertEquals(entity.coverUrl, domain.coverUrl)
        Assert.assertEquals(entity.description, domain.description)
        Assert.assertEquals(entity.rating, domain.rating, 0.1)
        Assert.assertEquals(entity.title, domain.title)
    }
}