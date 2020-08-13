package bobby.irawan.movieapp.presentation.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.movieapp.databinding.FragmentNowPlayingBinding
import bobby.irawan.movieapp.presentation.detail.MovieDetailActivity
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingFragment : Fragment(), NowPlayingAdapter.ClickListener {

    private var binding: FragmentNowPlayingBinding? = null
    private val viewModel by viewModel<NowPlayingViewModel>()
    private val adapter = NowPlayingAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
        viewModel.getNowPlayingMovies()
    }

    private fun initView() {
        binding?.recyclerViewNowPlaying?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movieItems.observe(viewLifecycleOwner) { movieItems ->
            adapter.setDataMovie(movieItems)
        }
        viewModel.snackbarMessage().observe(viewLifecycleOwner) { message ->
            showToast(message)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClickMovie(movieItem: MovieItem) {
        MovieDetailActivity.startActivity(requireContext(), movieItem)
    }

}