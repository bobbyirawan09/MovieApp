package bobby.irawan.movieapp.data.movies.model.nowplaying

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    val dates: NowPlayingDatesResponse? = null,
    val page: Int = 0,
    val results: List<NowPlayingResultResponse>? = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)