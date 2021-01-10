package mustafaozhan.github.com.ui.di.view

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.ui.eventlist.EventListFragment

@Suppress("unused")
@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesEventListFragment(): EventListFragment

}