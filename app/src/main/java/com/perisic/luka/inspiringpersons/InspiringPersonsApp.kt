package com.perisic.luka.inspiringpersons

import android.app.Application
import com.perisic.luka.inspiringpersons.data.localModule
import com.perisic.luka.inspiringpersons.data.repoModule
import com.perisic.luka.inspiringpersons.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InspiringPersonsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InspiringPersonsApp)
            modules(
                listOf(
                    localModule,
                    repoModule,
                    uiModule
                )
            )
        }
    }

}