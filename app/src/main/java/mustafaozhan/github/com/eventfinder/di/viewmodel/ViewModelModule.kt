package mustafaozhan.github.com.eventfinder.di.viewmodel

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.eventfinder.di.scope.ActivityScope
import mustafaozhan.github.com.eventfinder.ui.eventlist.EventListViewModel

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    internal fun providesEventListViewModel() = EventListViewModel()
}