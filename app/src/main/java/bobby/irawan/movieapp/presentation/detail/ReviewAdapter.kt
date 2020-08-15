package bobby.irawan.movieapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.databinding.ItemMovieBannerBinding
import bobby.irawan.movieapp.databinding.ItemMovieReviewBinding
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.presentation.popular.PopularAdapter
import bobby.irawan.movieapp.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 14/08/20.
 */
class DetailAdapter(private val listener: ClickListener?) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var movieReview = listOf<MovieItem>()

    fun setDataMovie(movieReview: List<MovieReview>) {
        this.movieReview = movieReview
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    inner class ViewHolder(private val binding: ItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItem) {
            with(binding) {
                textViewName.setGlideAttribute(movieItem.backdropUrl)
                textViewTitle.text = movieItem.title
                root.setOnClickListener { listener?.onClickMovie(movieItem) }
            }
        }
    }

    interface ClickListener {
        fun onClickMovie(movieItem: MovieItem)
    }
}