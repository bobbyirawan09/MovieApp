package bobby.irawan.movieapp.presentation.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.movieapp.databinding.FragmentNowPlayingBinding
import bobby.irawan.movieapp.presentation.detail.DetailActivity
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.*
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
        setUpView()
        observeViewModel()
        viewModel.getNowPlayingMovies()
    }

    private fun setUpView() {
        binding?.recyclerViewNowPlaying?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movieItems.observe(viewLifecycleOwner, ::onSuccessState)
        viewModel.snackbarMessage().observe(this, ::onFailedState)
    }

    private fun onSuccessState(movieItems: List<MovieItem>) {
        adapter.setDataMovie(movieItems)
        binding?.shimmer?.setGoneAndStop()
        binding?.recyclerViewNowPlaying?.orGone(movieItems)
        binding?.textViewEmptyDataMessage?.isShowEmptyInfo(movieItems)
    }

    private fun onFailedState(message: String) {
        showToast(message)
        binding?.shimmer?.setGoneAndStop()
        binding?.textViewEmptyDataMessage?.setVisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClickMovie(movieItem: MovieItem) {
        DetailActivity.startActivity(requireContext(), movieItem)
    }

}