package com.elkhami.flickerimagesearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import com.elkhami.flickerimagesearch.data.remote.responses.Photo

/**
 * Created by A.Elkhami on 18,July,2021
 */
class FlickerRemoteMediator(
    private val api: FlickerAPI,
    private val searchWord: String) :
    PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val pageNumber = params.key ?: 1

        return try {

            val response = api.searchFlickerPhotos(
                searchKeyword = searchWord,
                pageNumber = pageNumber
            )

            var pagedResponse: FlickerPhotosResponse? = null

            if (response.isSuccessful) {
                pagedResponse = response.body()
            }

            val data = pagedResponse?.photos

            val nextPageNumber: Int = pageNumber + 1

            LoadResult.Page(
                data = data?.photo.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}