package bobby.irawan.movieapp.presentation.toprated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.databinding.ItemMovieFavoriteBinding
import bobby.irawan.movieapp.databinding.ItemMovieInfoBinding
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class TopRatedAdapter(private val listener: ClickListener?): RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {

    private var movieItems = listOf<MovieItem>()

    fun setDataMovie(movieItems: List<MovieItem>) {
        this.movieItems = movieItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    inner class ViewHolder(private val binding: ItemMovieInfoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItem) {
            with(binding) {
                imageViewBanner.setGlideAttribute(movieItem.backdropUrl)
                textViewReleaseDate.text = movieItem.releaseDate
                textViewTitle.text = movieItem.title
                root.setOnClickListener { listener?.onClickMovie(movieItem) }
            }
        }
    }

    interface ClickListener {
        fun onClickMovie(movieItem: MovieItem)
    }
}