package mustafaozhan.github.com.di.viewmodel

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.api.ApiRepository
import mustafaozhan.github.com.db.EventDao
import mustafaozhan.github.com.di.scope.ActivityScope
import mustafaozhan.github.com.ui.eventdetail.EventDetailViewModel
import mustafaozhan.github.com.ui.eventlist.EventListViewModel
import mustafaozhan.github.com.ui.favoriteevents.FavoriteEventsViewModel

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    internal fun providesEventListViewModel(
        apiRepository: ApiRepository,
        eventDao: EventDao
    ) = EventListViewModel(
        apiRepository,
        eventDao
    )

    @Provides
    @ActivityScope
    internal fun providesEventDetailViewModel() = EventDetailViewModel()

    @Provides
    @ActivityScope
    internal fun providesFavoriteEventsViewModel(eventDao: EventDao) =
        FavoriteEventsViewModel(eventDao)
}