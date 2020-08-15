package bobby.irawan.movieapp.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.movieapp.AppController
import bobby.irawan.movieapp.R
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

fun ImageView.setForMovieBanner(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(radius = 20f))
    }

fun ImageView.setForMovieInfo(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(20f, 20f, 0f, 0f))
    }

fun ImageView.setForMovieFavorite(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(20f, 0f, 20f, 0f))
    }

fun ImageView.loadImage(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(0f))
    }

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun String?.orNoInfoString(): String =
    if (this.isNullOrEmpty()) AppController.getInstance()
        .getString(R.string.no_available_information) else this


fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun TextView.isShowEmptyInfo(data: List<*>?, action: () -> Unit = {}) = if (data.isNullOrEmpty()) {
    this.setVisible()
    action()
} else {
    this.setGone()
}

fun RecyclerView.orGone(data: List<*>?) = if (data.isNullOrEmpty()) {
    this.setGone()
} else this.setVisible()

fun ShimmerFrameLayout.setGoneAndStop() {
    this.setGone()
    this.stopShimmer()
}

fun String.asShowDate(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        formatter.format(parser.parse(this) ?: Date())
    } catch (e: Exception) {
        ""
    }
}