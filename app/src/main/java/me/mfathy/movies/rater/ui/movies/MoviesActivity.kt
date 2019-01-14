package me.mfathy.movies.rater.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies.*
import me.mfathy.movies.rater.R
import me.mfathy.movies.rater.domain.model.Movie
import me.mfathy.movies.rater.injection.ViewModelFactory
import me.mfathy.movies.rater.ui.state.Resource
import me.mfathy.movies.rater.ui.state.ResourceState
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

/**
 * MoviesActivity displays user movies library ordered by movies rating
 */
class MoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private var isRandomRatingStopped = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        AndroidInjection.inject(this)

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoviesViewModel::class.java)

        moviesAdapter = MoviesAdapter(this) { onMovieClick(it) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = moviesAdapter
    }

    override fun onStart() {
        super.onStart()

        /**
         * Observe changes on movies live data
         */
        moviesViewModel.getMoviesLiveData().observe(this,
            Observer<Resource<List<Movie>>> {
                it?.let { resource ->
                    handleMoviesResult(resource)
                }
            })

        /**
         * Observe updates after rating movies.
         */
        moviesViewModel.getRateLiveData().observe(this,
            Observer<Resource<Boolean>> {
                it?.let {
                    handleRatingAMovieResult(it)
                }
            })

        moviesViewModel.fetchMovies()
    }

    /**
     * RecyclerView on click listener
     */
    private fun onMovieClick(movie: Movie) {
        if (isRandomRatingStopped) showRatingDialog(movie)
        else Snackbar.make(contentLayout, "Please stop random rating first", Snackbar.LENGTH_SHORT).show()
    }

    private fun showRatingDialog(movie: Movie) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_give_rating, null)
        dialogBuilder.setView(dialogView)

        val dialogRatingInput = dialogView.findViewById<EditText>(R.id.inputRating)

        dialogBuilder.setTitle(movie.title)
        dialogBuilder.setMessage(getString(R.string.give_this_movie_a_new_rating))
        dialogBuilder.setPositiveButton(
            getString(R.string.rate)
        ) { dialog, _ ->
            if (dialogRatingInput.text.isBlank())
                dialog.dismiss()
            else {
                movie.rating = dialogRatingInput.text.toString().toDouble()
                moviesViewModel.rateMovie(movie)
                dialog.dismiss()
            }
        }

        dialogBuilder.show()
    }

    fun onRandomRatingClick(view: View) {
        when (view.id) {
            R.id.buttonRandomRating -> {
                if (isRandomRatingStopped) {
                    startRandomRating()
                    buttonRandomRating.text = getString(R.string.stop_random_rating)
                } else {
                    buttonRandomRating.text = getString(R.string.random_rating)
                }
                isRandomRatingStopped = !isRandomRatingStopped
            }
        }
    }

    /**
     * Start random rating.
     */
    private fun startRandomRating() {
        val randomMovieIndex = ThreadLocalRandom.current().nextInt(0, moviesAdapter.itemCount - 1)
        val randomDelay = ThreadLocalRandom.current().nextLong(5, 20)
        val movie = moviesAdapter.items[randomMovieIndex]
        movie.rating = ThreadLocalRandom.current().nextDouble(1.0, 10.0).roundTo(1)
        moviesViewModel.rateMovie(movie, true, randomDelay)
    }

    /**
     * Handles rating a movie and update UI
     */
    private fun handleRatingAMovieResult(resource: Resource<Boolean>) {
        when {
            resource.status == ResourceState.SUCCESS -> {
                resource.data?.let { isSuccess ->
                    if (isSuccess) {
                        val sortedByDescending = moviesAdapter.items.sortedByDescending { it.rating }
                        moviesAdapter.items = sortedByDescending
                        moviesAdapter.notifyDataSetChanged()
                    }

                    if (!isRandomRatingStopped) startRandomRating()
                }
            }
            resource.status == ResourceState.ERROR -> {
                resource.error?.let {
                    Snackbar.make(contentLayout, "Error rating a movie", Snackbar.LENGTH_SHORT).show()
                }
            }
            resource.status == ResourceState.LOADING -> {
            }
        }
    }

    /**
     * Handles get movies and update UI
     */
    private fun handleMoviesResult(resource: Resource<List<Movie>>) {
        when {
            resource.status == ResourceState.SUCCESS -> {
                resource.data?.let {

                    moviesAdapter.items = it
                    moviesAdapter.notifyDataSetChanged()

                    // UI
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                    errorTextView.visibility = View.INVISIBLE
                }

            }
            resource.status == ResourceState.LOADING -> {
                // UI
                recyclerView.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
                errorTextView.visibility = View.INVISIBLE
            }
            resource.status == ResourceState.ERROR -> {
                resource.error?.let {

                    // UI
                    recyclerView.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                    errorTextView.visibility = View.INVISIBLE
                }

            }
        }
    }

    /**
     * Extension function to round doubles to 1 digit fraction.
     */
    private fun Number.roundTo(numFractionDigits: Int) = String.format("%.${numFractionDigits}f", toDouble()).toDouble()
}
