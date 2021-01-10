package mustafaozhan.github.com.eventfinder.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.eventfinder.ui.eventlist.EventListFragment

@Suppress("unused")
@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesEventListFragment(): EventListFragment

}