package com.elkhami.flickerimagesearch.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elkhami.flickerimagesearch.BuildConfig
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by A.Elkhami on 07,July,2021
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideFlickerAPI(): FlickerAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(FlickerAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context)
    = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image_search)
                .error(R.drawable.ic_image_search)
        )
}