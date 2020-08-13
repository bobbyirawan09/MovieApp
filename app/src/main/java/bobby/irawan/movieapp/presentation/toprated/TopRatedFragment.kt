package bobby.irawan.movieapp.presentation.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.movieapp.databinding.FragmentTopRatedBinding
import bobby.irawan.movieapp.presentation.detail.MovieDetailActivity
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedFragment : Fragment(), TopRatedAdapter.ClickListener {

    private var binding: FragmentTopRatedBinding? = null
    private val viewModel by viewModel<TopRatedViewModel>()
    private val adapter = TopRatedAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
        viewModel.getTopRatedMovies()
    }

    private fun initView() {
        binding?.recyclerViewTopRated?.adapter = adapter
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