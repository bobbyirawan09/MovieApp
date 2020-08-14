package bobby.irawan.movieapp.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.movieapp.databinding.FragmentPopularBinding
import bobby.irawan.movieapp.presentation.detail.DetailActivity
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment(), PopularAdapter.ClickListener {

    private var binding: FragmentPopularBinding? = null
    private val viewModel by viewModel<PopularViewModel>()
    private val adapter = PopularAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModel()
        viewModel.getPopularMovies()
    }

    private fun setUpView() {
        binding?.recyclerViewPopular?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movieItems.observe(viewLifecycleOwner) { movieItems ->
            adapter.setDataMovie(movieItems)
        }
        viewModel.snackbarMessage().observe(this, ::showToast)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClickMovie(movieItem: MovieItem) {
        DetailActivity.startActivity(requireContext(), movieItem)
    }

}