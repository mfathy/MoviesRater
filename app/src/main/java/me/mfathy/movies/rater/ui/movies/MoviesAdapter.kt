package me.mfathy.movies.rater.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import me.mfathy.movies.rater.R
import me.mfathy.movies.rater.domain.model.Movie
import javax.inject.Inject

/**
 * Created by Mohammed Fathy on 17/12/2018.
 * dev.mfathy@gmail.com
 *
 * Movies recycler view adapter
 */
class MoviesAdapter @Inject constructor(val context: Context, val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var items: List<Movie> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = items[position]
        holder.titleTextView?.text = movie.title
        holder.descriptionTextView?.text = movie.description
        holder.ratingTextView?.text = "${movie.rating} / 10.0"

        holder.coverImageView?.let {
            Glide.with(context)
                .load(movie.coverUrl)
                .apply(RequestOptions.fitCenterTransform())
                .transition(DrawableTransitionOptions.withCrossFade(750))
                .into(it)
            it.contentDescription = movie.title
        }

        holder.itemView.setOnClickListener { clickListener(movie) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTextView: TextView? = view.findViewById(R.id.titleTextView)
        var descriptionTextView: TextView? = view.findViewById(R.id.descriptionTextView)
        var ratingTextView: TextView? = view.findViewById(R.id.ratingTextView)
        var coverImageView: ImageView? = view.findViewById(R.id.coverImageView)
    }
}