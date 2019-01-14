package me.mfathy.movies.rater.test.factory

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object AppDataFactory {
    fun randomDouble(): Double {
        return ThreadLocalRandom.current().nextInt().toDouble()
    }

    fun randomString(): String {
        return java.util.UUID.randomUUID().toString()
    }
}