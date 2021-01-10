package mustafaozhan.github.com.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.data.db.AppDatabase
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    internal fun providesEventDao(database: AppDatabase) =
        database.eventDao()

    @Provides
    @Singleton
    internal fun providesAppDatabase(
        applicationContext: Context
    ): AppDatabase = AppDatabase.createAppDatabase(applicationContext)
}
