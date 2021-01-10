package mustafaozhan.github.com.ui.di.viewmodel

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.ui.di.scope.ActivityScope
import mustafaozhan.github.com.ui.eventlist.EventListViewModel

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    internal fun providesEventListViewModel() = EventListViewModel()
}