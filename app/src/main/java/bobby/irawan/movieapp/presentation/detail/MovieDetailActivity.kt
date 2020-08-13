package bobby.irawan.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.databinding.ActivityMovieDetailBinding
import bobby.irawan.movieapp.presentation.model.MovieItem

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM_MOVIES = "ITEM_MOVIES"
        fun startActivity(context: Context, movieItem: MovieItem) {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(ITEM_MOVIES, movieItem)
            }
            context.startActivity(intent)
        }
    }
}