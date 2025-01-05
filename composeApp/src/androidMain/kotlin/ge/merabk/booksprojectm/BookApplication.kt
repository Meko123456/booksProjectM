package ge.merabk.booksprojectm

import android.app.Application
import ge.merabk.booksprojectm.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}