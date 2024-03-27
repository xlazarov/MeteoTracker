package com.example.meteortracker.util

import android.content.Context

object ThemeManager {
    private const val THEME_PREF = "theme_preference"
    private const val THEME_KEY = "theme_key"

    fun get(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(THEME_KEY, false)
    }

    fun set(context: Context, isDarkTheme: Boolean) {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkTheme).apply()
    }
}
