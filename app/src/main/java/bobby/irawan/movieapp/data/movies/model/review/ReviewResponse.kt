package bobby.irawan.movieapp.data.movies.model.review

import bobby.irawan.movieapp.data.movies.model.toprated.TopRatedResultResponse
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id: Int = 0,
    val page: Int = 0,
    val results: List<ReviewResultResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)