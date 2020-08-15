package bobby.irawan.movieapp.data.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
data class MovieResponse(
    val dates: MovieDatesResponse? = null,
    val id: Int = 0,
    val page: Int = 0,
    val results: List<MovieResultResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)