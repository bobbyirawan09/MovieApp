package bobby.irawan.movieapp.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.databinding.ItemMovieFavoriteBinding
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.utils.orNoInfoString
import bobby.irawan.movieapp.utils.setForMovieFavorite

class FavoriteAdapter(private val listener: ClickListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(dataFavorite: List<Favorite>) {
        differ.submitList(dataFavorite)
    }

    fun getItemByPosition(position: Int): Favorite {
        return differ.currentList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMovieFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> holder.bind(differ.currentList[position])
        }
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            with(binding) {
                textViewTitle.text = favorite.title.orNoInfoString()
                imageViewBanner.setForMovieFavorite(favorite.posterUrl)
                textViewReleaseDate.text = favorite.releaseDate.orNoInfoString()
                textViewSynopsis.text = favorite.overview.orNoInfoString()
                layoutVoteAverage.textViewVoteAverage.text = favorite.voteAverage.toString()
                textViewTotalVotes.text =
                    root.context.getString(R.string.vote_count_label, favorite.voteCount.toString())
                root.setOnClickListener { listener?.onClickFavorite(favorite) }
            }
        }
    }

    interface ClickListener {
        fun onClickFavorite(favorite: Favorite)
    }
}
