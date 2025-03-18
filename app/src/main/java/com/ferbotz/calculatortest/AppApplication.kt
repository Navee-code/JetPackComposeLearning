package com.ferbotz.calculatortest

import android.app.Application
import com.ferbotz.calculatortest.database.AppDb

class AppApplication : Application() {
    val db: AppDb by lazy { AppDb.getDatabase(this) }
    override fun onCreate() {
        super.onCreate()

    }
}