package com.example.meteortracker.presentation.map.di

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.osmdroid.config.Configuration
import org.osmdroid.config.IConfigurationProvider

@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @Provides
    fun provideMapConfig(@ApplicationContext appContext: Context): IConfigurationProvider? {
        val config = Configuration.getInstance()
        config.load(appContext, PreferenceManager.getDefaultSharedPreferences(appContext))
        return config
    }
}
