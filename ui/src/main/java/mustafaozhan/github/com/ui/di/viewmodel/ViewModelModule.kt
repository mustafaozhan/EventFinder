package mustafaozhan.github.com.ui.di.viewmodel

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.db.EventDao
import mustafaozhan.github.com.ui.di.scope.ActivityScope
import mustafaozhan.github.com.ui.eventdetail.EventDetailViewModel
import mustafaozhan.github.com.ui.eventlist.EventListViewModel

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
}