package mustafaozhan.github.com.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import dagger.android.AndroidInjection
import mustafaozhan.github.com.ui.R

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    @IdRes
    protected var containerId: Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun setHomeAsUpEnabled(enabled: Boolean) =
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)

    protected fun navigate(navDirections: NavDirections) =
        getNavigationController().navigate(
            navDirections, NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.enter_from_right)
                .setExitAnim(R.anim.exit_to_left)
                .setPopEnterAnim(R.anim.enter_from_left)
                .setPopExitAnim(R.anim.exit_to_right)
                .build()
        )

    protected fun getNavigationController() = findNavController(containerId)
}