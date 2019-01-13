package me.mfathy.movies.rater.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.mfathy.movies.rater.MoviesApplication
import me.mfathy.movies.rater.injection.modules.*
import javax.inject.Singleton

/**
 * Dagger application components
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        ViewModelsModule::class,
        DataModule::class,
        CacheModule::class,
        RemoteModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MoviesApplication)

}