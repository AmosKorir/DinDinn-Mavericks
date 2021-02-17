package com.food.dindinn

import androidx.multidex.MultiDexApplication
import com.airbnb.mvrx.Mavericks
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Din : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)

        startKoin {
            androidContext((this@Din))
            modules(
                listOf(
//                    DataModule.apiModule,
//                    DataModule.repositoryModule
                )
            )
        }
    }
}