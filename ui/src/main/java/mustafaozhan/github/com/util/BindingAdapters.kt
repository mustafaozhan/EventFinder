package mustafaozhan.github.com.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import mustafaozhan.github.com.ui.R
import mustafaozhan.github.com.ui.di.view.GlideApp

@BindingAdapter("visibility")
fun View.visibility(visible: Boolean) {
    visibility = if (visible) {
        bringToFront()
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("imageUrl", "placeholderImage", "imageRadius", requireAll = false)
fun ImageView.loadImageUrl(
    imageUrl: String?,
    placeholderImage: Drawable?,
    imageCornerRadius: Float?
) {
    GlideApp
        .with(context)
        .load(imageUrl)
        .apply {
            if (imageCornerRadius != null) {
                transform(CenterCrop(), RoundedCorners(imageCornerRadius.toInt()))
            } else {
                centerCrop()
            }
        }
        .placeholder(placeholderImage)
        .error(ContextCompat.getDrawable(context, R.drawable.ic_glide_error_placeholder))
        .into(this)
}
