package de.giphy_clean_architecture

import android.app.Application
import de.giphy_clean_architecture.inject.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GiphyArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GiphyArchApplication)
            modules(appComponent)
        }

        Timber.plant(Timber.DebugTree())
    }
}