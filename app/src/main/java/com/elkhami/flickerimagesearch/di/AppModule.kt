package com.elkhami.flickerimagesearch.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elkhami.flickerimagesearch.BuildConfig
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDAO
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDataBase
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import com.elkhami.flickerimagesearch.data.repository.DefaultRepository
import com.elkhami.flickerimagesearch.data.repository.Repository
import com.elkhami.flickerimagesearch.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideSavedPhotosDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SavedPhotoDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideSavedPhotosDAO(dataBase: SavedPhotoDataBase) = dataBase.savedPhotoDAO()

    @Singleton
    @Provides
    fun provideFlickerAPI(): FlickerAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .build()
            .create(FlickerAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(dao: SavedPhotoDAO, api: FlickerAPI) =
        DefaultRepository(dao, api) as Repository

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image_search)
                .error(R.drawable.ic_image_search)
        )
}