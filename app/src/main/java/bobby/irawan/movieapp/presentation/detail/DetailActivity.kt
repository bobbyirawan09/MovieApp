package bobby.irawan.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.observe
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.databinding.ActivityDetailBinding
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.setGlideAttribute
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpListener()
        observeViewModel()
        viewModel.getFromBundle(intent)
        viewModel.checkIsFavorite()
    }

    private fun setUpListener() {
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_detail_favorite -> {
                    viewModel.onFavoriteClick()
                    true
                }
                R.id.action_detail_share -> {
                    onShareMovie()
                    true
                }
                else -> false
            }
        }
    }

    private fun onShareMovie() {
        val message = ""
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_label))
            .setText(message)
            .startChooser()
    }

    private fun observeViewModel() {
        viewModel.snackbarMessage().observe(this, ::showToast)
        viewModel.isFavorite.observe(this, ::onSetFavoriteState)
        viewModel.reviews.observe(this) {
            //Show review
        }
        viewModel.movieItem.observe(this, ::setUpView)
    }

    private fun onSetFavoriteState(isFavorite: Boolean) {
        binding.bottomAppBar.menu
            .findItem(R.id.action_detail_favorite)
            .setIcon(
                if (isFavorite) {
                    R.drawable.ic_baseline_favorited
                } else {
                    R.drawable.ic_baseline_favorite
                }
            )
    }

    private fun setUpView(movieItem: MovieItem) {
        with(binding) {
            toolbar.title = movieItem.title
            textViewDescription.text = movieItem.overview
            textViewReleaseDate.text = movieItem.releaseDate
            textViewTitle.text = movieItem.title
            imageViewBanner.setGlideAttribute(movieItem.backdropUrl)
        }
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