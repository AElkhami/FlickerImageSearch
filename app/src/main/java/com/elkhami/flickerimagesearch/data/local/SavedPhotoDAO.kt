package com.elkhami.flickerimagesearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by A.Elkhami on 07,July,2021
 */
@Dao
interface SavedPhotoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoToDB(photo: SavedPhoto): Long

    @Delete
    suspend fun deleteSavedPhoto(photo: SavedPhoto): Int

    @Query("SELECT * FROM saved_photo WHERE id = :imageId")
    fun getSavedPhoto(imageId : Int): LiveData<SavedPhoto>

    @Query("SELECT * FROM saved_photo")
    fun getAllSavedPhotos(): LiveData<List<SavedPhoto>>

}