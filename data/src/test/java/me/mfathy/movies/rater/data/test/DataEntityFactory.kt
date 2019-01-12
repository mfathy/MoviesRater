package me.mfathy.movies.rater.data.test

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Mohammed Fathy on 11/01/2019.
 * dev.mfathy@gmail.com
 */
object DataEntityFactory {
    fun randomDouble(): Double {
        return ThreadLocalRandom.current().nextDouble(0.0, 5.0)
    }

    fun randomString(): String {
        return java.util.UUID.randomUUID().toString()
    }
}