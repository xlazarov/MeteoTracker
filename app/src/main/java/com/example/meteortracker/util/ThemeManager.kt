package com.example.meteortracker.util

import android.content.Context
import com.example.meteortracker.R

/**
 * Handles theme and hero image preferences for the app, providing functionality to switch between light and dark themes,
 * and to select and retrieve hero images according to the current theme.
 */
object ThemeManager {
    private const val THEME_PREF = "theme_preference"
    private const val THEME_KEY = "theme_key"

    private const val HERO_IMAGE_LIGHT_KEY = "hero_image_light_key"
    private const val HERO_IMAGE_DARK_KEY = "hero_image_dark_key"

    private val HERO_OPTIONS_DARK = listOf(
        R.drawable.hero_dark1,
        R.drawable.hero_dark2,
        R.drawable.hero_dark3
    )
    private val HERO_OPTIONS_LIGHT = listOf(
        R.drawable.hero_light1,
        R.drawable.hero_light2,
        R.drawable.hero_light3,
        R.drawable.hero_light4
    )

    fun getTheme(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(THEME_KEY, false)
    }

    fun setTheme(context: Context, isDarkTheme: Boolean) {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkTheme).apply()
    }

    fun getHeroImage(context: Context, isDarkTheme: Boolean): Int {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        return if (isDarkTheme) {
            sharedPreferences.getInt(HERO_IMAGE_DARK_KEY, R.drawable.hero_dark3)
        } else {
            sharedPreferences.getInt(HERO_IMAGE_LIGHT_KEY, R.drawable.hero_light2)
        }
    }

    fun setHeroImage(context: Context, isDarkTheme: Boolean, resourceId: Int) {
        val sharedPreferences = context.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE)
        if (isDarkTheme) {
            sharedPreferences.edit().putInt(HERO_IMAGE_DARK_KEY, resourceId).apply()
        } else {
            sharedPreferences.edit().putInt(HERO_IMAGE_LIGHT_KEY, resourceId).apply()
        }
    }

    fun getHeroOptions(isDarkTheme: Boolean): List<Int> {
        return if (isDarkTheme) {
            HERO_OPTIONS_DARK
        } else {
            HERO_OPTIONS_LIGHT
        }
    }
}
