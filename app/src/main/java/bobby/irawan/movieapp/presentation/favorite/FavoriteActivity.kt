package bobby.irawan.movieapp.presentation.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import bobby.irawan.movieapp.databinding.ActivityFavoriteBinding
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.utils.isShowEmptyInfo
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModel<FavoriteViewModel>()
    private val adapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        obsereViewModel()
        viewModel.getAllFavourite()
    }

    private fun initView() {
        binding.recyclerViewFavorite.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerViewFavorite.adapter = adapter
    }

    private fun obsereViewModel() {
        viewModel.favouriteResult.observe(this) { favorites ->
            onUpdateData(favorites)
        }
        viewModel.snackbarMessage().observe(this) { message ->
            showToast(message)
        }
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

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FavoriteActivity::class.java)
            context.startActivity(intent)
        }
    }
}