package com.elkhami.flickerimagesearch.data.repository

import androidx.lifecycle.LiveData
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.local.SavedPhotoDAO
import com.elkhami.flickerimagesearch.data.remote.api.FlickerAPI
import javax.inject.Inject

/**
 * Created by A.Elkhami on 11,July,2021
 */
class DefaultRepository @Inject constructor(
    private val dao: SavedPhotoDAO,
    private val api: FlickerAPI
): Repository {

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

    override suspend fun searchFlickerWithKeyword(searchWord: String) {
        api.searchFlickerPhotos(searchKeyword = searchWord)
    }

}