package bobby.irawan.movieapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.databinding.ItemMovieReviewBinding
import bobby.irawan.movieapp.presentation.model.MovieReview

/**
 * Created by bobbyirawan09 on 14/08/20.
 */
class ReviewAdapter :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private var movieReviews = listOf<MovieReview>()

    fun setDataReview(movieReview: List<MovieReview>) {
        this.movieReviews = movieReview
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieReviews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieReviews[position])
    }

    inner class ViewHolder(private val binding: ItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieReview: MovieReview) {
            with(binding) {
                textViewName.text = movieReview.author
                textViewReview.text = movieReview.content
            }
        }
    }
}