package com.elkhami.flickerimagesearch.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by A.Elkhami on 07,July,2021
 */
@Entity(tableName = "saved_photo")
data class SavedPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val imageUrl: String? = null,
    val imageTitle: String? = null
)