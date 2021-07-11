package com.elkhami.flickerimagesearch.data.repository

import androidx.lifecycle.LiveData
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDAO
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import com.elkhami.flickerimagesearch.other.Constants.NETWORK_ERROR
import com.elkhami.flickerimagesearch.other.Constants.UNKNOWN_ERROR
import com.elkhami.flickerimagesearch.other.Resource
import javax.inject.Inject

/**
 * Created by A.Elkhami on 11,July,2021
 */
class DefaultRepository @Inject constructor(
    private val dao: SavedPhotoDAO,
    private val api: FlickerAPI
) : Repository {

    override suspend fun insertPhotoToDB(photo: SavedPhoto) {
        dao.insertPhotoToDB(photo)
    }

    override suspend fun deleteSavedPhoto(photo: SavedPhoto) {
        dao.deleteSavedPhoto(photo)
    }

    override fun getSavedPhoto(imageId: Int): LiveData<SavedPhoto> {
        return dao.getSavedPhoto(imageId)
    }

    override fun getAllSavedPhotos(): LiveData<List<SavedPhoto>> {
        return dao.getAllSavedPhotos()
    }

    override suspend fun searchFlickerWithKeyword(searchWord: String)
            : Resource<FlickerPhotosResponse> {
        return try {
            val response = api.searchFlickerPhotos(searchKeyword = searchWord)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: return Resource.Failed(null, UNKNOWN_ERROR)
            } else {
                return Resource.Failed(null, UNKNOWN_ERROR)
            }
        } catch (e: Exception) {
            Resource.Failed(
                null,
                NETWORK_ERROR
            )
        }
    }


}