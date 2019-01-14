package me.mfathy.movies.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.mfathy.movies.rater.domain.repository.MoviesRepository
import me.mfathy.movies.rater.injection.modules.ViewModelsModule
import me.mfathy.movies.rater.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        ViewModelsModule::class,
        TestUiModule::class,
        TestRemoteModule::class]
)
interface TestApplicationComponent {

    fun moviesRepository(): MoviesRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}