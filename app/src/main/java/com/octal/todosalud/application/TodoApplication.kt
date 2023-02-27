package com.octal.todosalud.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.octal.todosalud.utility.LocaleHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApplication : Application(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(base))

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.setLocale(this)
    }
}
