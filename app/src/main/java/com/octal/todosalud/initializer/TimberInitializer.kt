package com.octal.todosalud.initializer

import android.content.Context
import androidx.startup.Initializer
import com.octal.todosalud.BuildConfig
import timber.log.Timber

@Suppress("unused") // used in manifest
class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    // firebase log
                }
            })
        }
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}
