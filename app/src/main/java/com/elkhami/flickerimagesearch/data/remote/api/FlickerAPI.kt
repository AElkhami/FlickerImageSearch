package com.elkhami.flickerimagesearch.data.remote.api

import com.elkhami.flickerimagesearch.BuildConfig
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import com.elkhami.flickerimagesearch.other.Constants.FLICKER_SEARCH_METHOD
import com.elkhami.flickerimagesearch.other.Constants.RESPONSE_FORMAT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by A.Elkhami on 07,July,2021
 */
interface FlickerAPI {

    @GET("services/rest")
    suspend fun searchFlickerPhotos(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("method")
        method: String = FLICKER_SEARCH_METHOD,
        @Query("format")
        format: String = RESPONSE_FORMAT,
        @Query("page")
        pageNumber: Int = 1,
        @Query("nojsoncallback")
        nojsoncallback: Int = 1,
        @Query("text")
        searchKeyword: String
    ): Response<FlickerPhotosResponse>

}