package bobby.irawan.movieapp.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.databinding.ActivityHomeBinding
import bobby.irawan.movieapp.presentation.favorite.FavoriteActivity
import bobby.irawan.movieapp.presentation.nowplaying.NowPlayingFragment
import bobby.irawan.movieapp.presentation.popular.PopularFragment
import bobby.irawan.movieapp.presentation.toprated.TopRatedFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPopularMoviesFragment()
        initTopRatedFragment()
        initNowPlayingFragment()
    }

    private fun initPopularMoviesFragment() {
        changeFragment(
            binding.frameLayoutPopular.id,
            PopularFragment()
        )
    }

    private fun initTopRatedFragment() {
        changeFragment(binding.frameLayoutTopRated.id, TopRatedFragment())
    }

    private fun initNowPlayingFragment() {
        changeFragment(binding.frameLayoutNowPlaying.id, NowPlayingFragment())
    }

    private fun changeFragment(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(layoutId, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourite -> {
                FavoriteActivity.startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}