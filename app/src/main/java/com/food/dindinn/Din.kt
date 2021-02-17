package com.food.dindinn

import android.app.Application
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.airbnb.mvrx.Mavericks

class Din : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}