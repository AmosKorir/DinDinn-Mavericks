package com.food.dindinn

import android.app.Application
import com.airbnb.mvrx.Mavericks

class Din :Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}