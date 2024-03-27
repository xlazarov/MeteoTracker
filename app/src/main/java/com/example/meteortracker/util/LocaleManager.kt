package com.example.meteortracker.util

import android.content.Context
import java.util.Locale

object LocaleManager {
    private const val LOCALE_PREF = "locale_preference"
    private const val LOCALE_KEY = "locale_key"

    fun get(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(LOCALE_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LOCALE_KEY, "en") ?: "en"
    }

    fun set(context: Context, languageCode: String): Context {
        val sharedPreferences = context.getSharedPreferences(LOCALE_PREF, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(LOCALE_KEY, languageCode).apply()

        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    fun update(context: Context): Context {
        val lang = get(context)
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }
}