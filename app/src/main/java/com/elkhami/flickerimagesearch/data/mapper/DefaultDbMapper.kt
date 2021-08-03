package com.elkhami.flickerimagesearch.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.remote.responses.Photo

/**
 * Created by A.Elkhami on 28,July,2021
 */
class DefaultDbMapper: DataBaseMapper {

    override fun mapPagedSavedPhotoToPagedPhoto(savedPhoto: PagingData<SavedPhoto>): PagingData<Photo> {
        return savedPhoto.map {
            with(it){
                Photo(
                    id = flickerPhotoId,
                    title = photoTitle,
                    photoURL = photoUrl
                )
            }
        }
    }
}