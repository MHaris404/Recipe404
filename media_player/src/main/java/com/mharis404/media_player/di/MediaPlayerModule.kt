package com.mharis404.media_player.di

import com.mharis404.media_player.navigation.MediaPlayerFeatureAPi
import com.mharis404.media_player.navigation.MediaPlayerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MediaPlayerModule {

    @Provides
    fun provideMediaPlayerFeatureApi(): MediaPlayerFeatureAPi {
        return MediaPlayerImpl()
    }

}