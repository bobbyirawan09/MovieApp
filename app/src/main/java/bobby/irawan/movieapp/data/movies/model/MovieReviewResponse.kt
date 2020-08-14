package bobby.irawan.movieapp.data.movies.model

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<MovieReviewResultResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)