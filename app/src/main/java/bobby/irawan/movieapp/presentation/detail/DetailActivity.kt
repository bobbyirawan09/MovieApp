package bobby.irawan.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.databinding.ActivityDetailBinding
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()
    private val adapter = ReviewAdapter()
    private lateinit var title: String
    private lateinit var voteAverage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpRecyclerView()
        setUpListener()
        observeViewModel()
        viewModel.getFromBundle(intent)
        viewModel.checkIsFavorite()
        viewModel.getReviews()
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewReview.adapter = adapter
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
        val message = applicationContext.getString(R.string.share_message, title, voteAverage)
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_label))
            .setText(message)
            .startChooser()
    }

    private fun observeViewModel() {
        viewModel.snackbarMessage().observe(this, ::onFailedState)
        viewModel.isFavorite.observe(this, ::onSetFavoriteState)
        viewModel.reviews.observe(this, ::onSuccessState)
        viewModel.movieItem.observe(this, ::setUpView)
    }

    private fun onSuccessState(movieReviews: List<MovieReview>) {
        binding.textViewReviewLabel.text =
            applicationContext.getString(
                R.string.total_review_label,
                movieReviews.size.toString()
            )
        adapter.setDataReview(movieReviews)
        binding.shimmer.setGoneAndStop()
        binding.recyclerViewReview.orGone(movieReviews)
        binding.textViewEmptyDataMessage.isShowEmptyInfo(movieReviews)
    }

    private fun onFailedState(message: String) {
        showToast(message)
        binding.shimmer.setGoneAndStop()
        binding.textViewEmptyDataMessage.setVisible()
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
            title = movieItem.title
            voteAverage = movieItem.voteAverage.toString()
            toolbar.title = title
            textViewDescription.text = movieItem.overview
            textViewDescription.apply {
                addShowMoreText(getString(R.string.label_text_expand))
                addShowLessText(getString(R.string.label_text_collapse))
                setShowMoreColor(ContextCompat.getColor(root.context, R.color.red_pink_color))
                setShowLessTextColor(ContextCompat.getColor(root.context, R.color.red_pink_color))
                setShowingLine(7)
            }
            imageViewPoster.setForMovieBanner(movieItem.posterUrl)
            layoutVoteAverage.textViewVoteAverage.text = movieItem.voteAverage.toString()
            textViewTotalVotes.text =
                applicationContext.getString(R.string.vote_label, movieItem.voteCount.toString())
            textViewReleaseDate.text = movieItem.releaseDate
            imageViewBanner.loadImage(movieItem.backdropUrl)
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