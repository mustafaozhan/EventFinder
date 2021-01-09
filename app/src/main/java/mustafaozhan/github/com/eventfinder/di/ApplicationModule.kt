package mustafaozhan.github.com.eventfinder.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.eventfinder.EventFinderApp
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun providesContext(application: EventFinderApp): Context =
        application.applicationContext
}