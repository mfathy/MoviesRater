package me.mfathy.movies.rater.ui.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import me.mfathy.movies.rater.R
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.test.TestApplication
import me.mfathy.movies.rater.test.factory.AppMovieDataFactory
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Mohammed Fathy on 14/01/2019.
 * dev.mfathy@gmail.com
 *
 * Espresso ui test for MoviesActivity
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MoviesActivityTest {


    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule<MoviesActivity>(MoviesActivity::class.java, false, false)

    private val movies = AppMovieDataFactory.makeMovieEntityList(2)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        stubMoviesRepositoryGetMovies(Flowable.just(movies))
        stubMoviesRepositoryRateMovie()
    }

    @Test
    fun moviesActivityTest() {
        mActivityTestRule.launchActivity(null)

        Thread.sleep(3000)

        val button = Espresso.onView(
            Matchers.allOf(ViewMatchers.withId(R.id.buttonRandomRating), ViewMatchers.isDisplayed())
        )
        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        button.check(ViewAssertions.matches(ViewMatchers.withText("Random Rating")))

        button.perform(click())

        Thread.sleep(3000)

        button.check(ViewAssertions.matches(ViewMatchers.withText("Stop random rating")))

        button.perform(click())
    }

    private fun stubMoviesRepositoryGetMovies(flowable: Flowable<MutableList<Movie>>?) {
        whenever(TestApplication.appComponent().moviesRepository().getMovies()).thenReturn(flowable)
    }

    private fun stubMoviesRepositoryRateMovie() {
        whenever(TestApplication.appComponent().moviesRepository().rateMovie(any())).thenReturn(Completable.complete())
    }
}