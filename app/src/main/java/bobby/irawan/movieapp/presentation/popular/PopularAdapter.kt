package bobby.irawan.movieapp.presentation.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.databinding.ItemMovieBannerBinding
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.setForMovieBanner

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class PopularAdapter(private val listener: ClickListener?) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var movieItems = listOf<MovieItem>()

    fun setDataMovie(movieItems: List<MovieItem>) {
        this.movieItems = movieItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    inner class ViewHolder(private val binding: ItemMovieBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItem) {
            with(binding) {
                imageViewBanner.setForMovieBanner(movieItem.backdropUrl)
                textViewTitle.text = movieItem.title
                layoutVoteAverage.textViewVoteAverage.text = movieItem.voteAverage.toString()
                root.setOnClickListener { listener?.onClickMovie(movieItem) }
            }
        }
    }

    interface ClickListener {
        fun onClickMovie(movieItem: MovieItem)
    }
}