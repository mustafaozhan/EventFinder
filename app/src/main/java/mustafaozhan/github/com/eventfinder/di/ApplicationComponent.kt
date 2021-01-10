package mustafaozhan.github.com.eventfinder.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import mustafaozhan.github.com.eventfinder.EventFinderApp
import mustafaozhan.github.com.eventfinder.di.scope.ActivityScope
import mustafaozhan.github.com.eventfinder.di.scope.FragmentScope
import javax.inject.Singleton

@Singleton
@ActivityScope
@FragmentScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityInjectionModule::class,
        FragmentInjectionModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: EventFinderApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: EventFinderApp)
}