package com.elkhami.flickerimagesearch.data.mapper

import androidx.paging.PagingData
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import javax.inject.Inject

/**
 * Created by A.Elkhami on 28,July,2021
 */

interface DataBaseMapper{
    fun mapSavedPhotoToPhoto(savedPhoto: PagingData<SavedPhoto>): PagingData<Photo>
}