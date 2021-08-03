package com.elkhami.flickerimagesearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDAO

/**
 * Created by A.Elkhami on 28,July,2021
 */
class SavedPhotoPagingSource(
    private val dao: SavedPhotoDAO
): PagingSource<Int, SavedPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedPhoto> {
        val position = params.key ?: 0
        val savedPhotos = dao.getAllSavedPhotos()

        return LoadResult.Page(
            //todo handle prev key and next key.
            data = savedPhotos,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, SavedPhoto>): Int? = null

}