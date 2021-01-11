package mustafaozhan.github.com.di.view

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.ui.eventdetail.EventDetailFragment
import mustafaozhan.github.com.ui.eventlist.EventListFragment
import mustafaozhan.github.com.ui.favoriteevents.FavoriteEventsFragment

@Suppress("unused")
@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesEventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun contributesEventDetailFragment(): EventDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesFavoriteEventsFragment(): FavoriteEventsFragment
}