package mustafaozhan.github.com.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun View.visibility(visible: Boolean) {
    visibility = if (visible) {
        bringToFront()
        View.VISIBLE
    } else {
        View.GONE
    }
}
