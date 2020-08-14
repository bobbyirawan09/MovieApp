package bobby.irawan.movieapp.presentation.model

import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity

/**
 * Created by bobbyirawan09 on 14/08/20.
 */
data class Favorite(
    var id: Int,
    var movieId: Int,
    var title: String,
    var releaseDate: String,
    var imageUrl: String,
    var overview: String
) {
    companion object {
        fun from(favoriteEntity: FavoriteEntity) = Favorite(
            favoriteEntity.id,
            favoriteEntity.movieId,
            favoriteEntity.title,
            favoriteEntity.releaseDate,
            favoriteEntity.imageUrl,
            favoriteEntity.overview
        )
    }
}