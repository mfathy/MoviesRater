package me.mfathy.movies.rater.data.store.cache.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CachedMovies")
data class CachedMovie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    val id: String = "",
    @ColumnInfo(name = "movie_title")
    val title: String = "",
    @ColumnInfo(name = "movie_cover_url")
    val coverUrl: String = "",
    @ColumnInfo(name = "movie_description")
    val description: String = "",
    @ColumnInfo(name = "movie_rating")
    var rating: Double = 0.0
)
