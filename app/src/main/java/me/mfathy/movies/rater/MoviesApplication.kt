package me.mfathy.movies.rater

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.mfathy.movies.rater.injection.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Base Application class >> used to inject dagger modules to use DI.
 */
class MoviesApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupDagger()

    }

    private fun setupDagger() {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

}
