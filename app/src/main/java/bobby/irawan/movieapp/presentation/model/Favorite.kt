package bobby.irawan.movieapp.presentation.model

import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity

/**
 * Created by bobbyirawan09 on 14/08/20.
 */
data class Favorite(
    var movieId: Int = 0,
    var title: String = "",
    var releaseDate: String = "",
    var posterUrl: String = "",
    var backdropUrl: String = "",
    var overview: String = ""
) {
    companion object {
        fun from(favoriteEntity: FavoriteEntity) = Favorite(
            favoriteEntity.movieId,
            favoriteEntity.title,
            favoriteEntity.releaseDate,
            favoriteEntity.posterUrl,
            favoriteEntity.backdropUrl,
            favoriteEntity.overview
        )
    }
}