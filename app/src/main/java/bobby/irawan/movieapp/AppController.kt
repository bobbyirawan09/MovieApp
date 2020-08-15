package bobby.irawan.movieapp

import android.app.Application
import android.content.Context
import bobby.irawan.movieapp.di.dataModule
import bobby.irawan.movieapp.di.domainModule
import bobby.irawan.movieapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class AppController : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(dataModule, domainModule, presentationModule)
        }
    }

    companion object {
        private lateinit var instance: AppController

        fun getInstance(): Context = instance.applicationContext
    }
}