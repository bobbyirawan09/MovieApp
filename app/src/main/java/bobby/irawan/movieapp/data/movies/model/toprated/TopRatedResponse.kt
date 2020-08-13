package bobby.irawan.movieapp.data.movies.model.toprated

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    val page: Int = 0,
    val results: List<TopRatedResultResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)