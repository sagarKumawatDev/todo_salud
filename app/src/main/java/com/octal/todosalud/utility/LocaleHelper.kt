package com.octal.todosalud.utility

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import java.util.*


object LocaleHelper {

    fun getLanguage(context: Context?):String{
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
       return preferences.getString("SELECTED_LANGUAGE","en")?:"en"

    }

    fun setLocale(context: Context?):Context{
        return setLocale(context, getLanguage(context))!!
    }

    fun setLocale(context: Context?, language: String): Context? {
        Log.d("Locale", "setLocale: $language")
        persist(context!!,language)
        // updating the language for devices above android nougat
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
        // for devices having lower version of android os
    }


    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("SELECTED_LANGUAGE", language)
        editor.apply()
    }


    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.getConfiguration()
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        return context
    }

}