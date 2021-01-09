package mustafaozhan.github.com.eventfinder

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import mustafaozhan.github.com.eventfinder.di.DaggerApplicationComponent
import javax.inject.Inject

class EventFinderApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
