package bobby.irawan.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import bobby.irawan.movieapp.databinding.ActivityDetailBinding
import bobby.irawan.movieapp.presentation.model.MovieItem

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ITEM_MOVIES = "ITEM_MOVIES"
        fun startActivity(context: Context, movieItem: MovieItem) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(ITEM_MOVIES, movieItem)
            }
            context.startActivity(intent)
        }
    }
}