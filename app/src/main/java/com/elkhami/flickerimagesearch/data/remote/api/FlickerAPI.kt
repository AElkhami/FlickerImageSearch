package com.elkhami.flickerimagesearch.data.remote.api

import com.elkhami.flickerimagesearch.BuildConfig
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by A.Elkhami on 07,July,2021
 */
interface FlickerAPI {

    @GET("flickr.photos.search")
    suspend fun searchFlickerPhotos(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("text")
        searchKeyword: String
    ): Response<FlickerPhotosResponse>

}