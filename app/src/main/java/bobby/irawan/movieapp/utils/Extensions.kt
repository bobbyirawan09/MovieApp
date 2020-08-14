package bobby.irawan.movieapp.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import bobby.irawan.movieapp.AppController
import bobby.irawan.movieapp.R
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

fun ImageView.setGlideAttribute(imageUrl: String) =
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_broken)
        .fallback(R.drawable.ic_image_broken)
        .into(this)

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

fun String.asShowDate(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        formatter.format(parser.parse(this) ?: Date())
    } catch (e: Exception) {
        ""
    }
}