package bobby.irawan.movieapp.presentation.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import bobby.irawan.movieapp.databinding.ActivityFavoriteBinding
import bobby.irawan.movieapp.presentation.detail.DetailActivity
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.isShowEmptyInfo
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity(), FavoriteAdapter.ClickListener {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModel<FavoriteViewModel>()
    private val adapter = FavoriteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpView()
        obsereViewModel()
        viewModel.getAllFavourite()
    }

    private fun setUpView() {
        binding.recyclerViewFavorite.adapter = adapter
    }

    private fun obsereViewModel() {
        viewModel.favoriteResult.observe(this, ::onUpdateData)
        viewModel.snackbarMessage().observe(this, ::showToast)
    }

    private fun onUpdateData(favourites: List<Favorite>) {
        adapter.submitList(favourites)
        binding.textViewEmptyDataMessage.isShowEmptyInfo(favourites)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickFavorite(favorite: Favorite) {
        DetailActivity.startActivity(this, MovieItem.fromFavorite(favorite))
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FavoriteActivity::class.java)
            context.startActivity(intent)
        }
    }
}