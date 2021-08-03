package com.elkhami.flickerimagesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDAO
import com.elkhami.flickerimagesearch.data.paging.FlickerRemoteMediator
import com.elkhami.flickerimagesearch.data.paging.SavedPhotoPagingSource
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import com.elkhami.flickerimagesearch.other.Constants.PAGE_SIZE
import com.elkhami.flickerimagesearch.other.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by A.Elkhami on 11,July,2021
 */
class DefaultRepository @Inject constructor(
    private val dao: SavedPhotoDAO,
    private val api: FlickerAPI
) : Repository {

    override suspend fun insertPhotoToDB(photo: SavedPhoto) =
        dao.insertPhotoToDB(photo)


    override suspend fun deleteSavedPhoto(photo: SavedPhoto) =
        dao.deleteSavedPhoto(photo)


    override fun getSavedPhoto(imageId: Int): LiveData<SavedPhoto> {
        return dao.getSavedPhoto(imageId)
    }

    override fun getAllSavedPhotos()
            : Flow<PagingData<SavedPhoto>> = Pager(
        config = PagingConfig(
            PAGE_SIZE,
            PREFETCH_DISTANCE
        ),
        pagingSourceFactory = { SavedPhotoPagingSource(dao) }
    ).flow

    override suspend fun getPaginatingData(searchWord: String)
            : Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(
            PAGE_SIZE,
            PREFETCH_DISTANCE
        ),
        pagingSourceFactory = { FlickerRemoteMediator(api, searchWord) }
    ).flow

}