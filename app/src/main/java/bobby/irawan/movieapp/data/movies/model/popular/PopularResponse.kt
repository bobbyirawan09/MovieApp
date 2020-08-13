package bobby.irawan.movieapp.data.movies.model.popular

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    val page: Int = 0,
    val results: List<PopularResultResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)