package com.elkhami.flickerimagesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import kotlinx.coroutines.flow.Flow


/**
 * Created by A.Elkhami on 11,July,2021
 */
interface Repository {

    suspend fun insertPhotoToDB(photo: SavedPhoto): Long

    suspend fun deleteSavedPhoto(photo: SavedPhoto): Int

    fun getSavedPhoto(imageId : Int): LiveData<SavedPhoto>

    fun getAllSavedPhotos(): LiveData<List<SavedPhoto>>

    suspend fun getPaginatingData(searchWord :String) : Flow<PagingData<Photo>>

}