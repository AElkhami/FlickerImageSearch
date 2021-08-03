package com.elkhami.flickerimagesearch.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by A.Elkhami on 07,July,2021
 */
@Parcelize
@Entity(tableName = "saved_photo")
data class SavedPhoto(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var flickerPhotoId: String? = null,
    var photoUrl: String? = null,
    var photoTitle: String? = null,
    var isPhotosSaved: Boolean = false
): Parcelable