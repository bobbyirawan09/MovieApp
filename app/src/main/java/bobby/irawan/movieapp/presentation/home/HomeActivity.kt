package bobby.irawan.movieapp.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import bobby.irawan.movieapp.databinding.ActivityHomeBinding
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
}