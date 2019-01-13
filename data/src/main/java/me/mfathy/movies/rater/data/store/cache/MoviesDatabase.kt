package me.mfathy.movies.rater.data.store.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.mfathy.movies.rater.data.store.cache.model.CachedMovie

/**
 * MoviesDatabase: the room database initializer.
 */
@Database(entities = [CachedMovie::class], version = 1, exportSchema = true)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        private var INSTANCE: MoviesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): MoviesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesDatabase::class.java, "movies.db"
                        ).build()
                    }
                    return INSTANCE as MoviesDatabase
                }
            }
            return INSTANCE as MoviesDatabase
        }
    }

}