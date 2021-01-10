package mustafaozhan.github.com.eventfinder.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.eventfinder.ui.MainActivity

@Suppress("unused")
@Module
abstract class ActivityInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}