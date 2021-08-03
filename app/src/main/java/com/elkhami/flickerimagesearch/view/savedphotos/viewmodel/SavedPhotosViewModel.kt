package com.elkhami.flickerimagesearch.view.savedphotos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkhami.flickerimagesearch.data.mapper.DataBaseMapper
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import com.elkhami.flickerimagesearch.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by A.Elkhami on 23,July,2021
 */
@HiltViewModel
class SavedPhotosViewModel @Inject constructor(
    repository: Repository,
    private val dbMapper: DataBaseMapper
) : ViewModel() {


    val getSavedPhotos : LiveData<PagingData<Photo>> =
        repository
            .getAllSavedPhotos()
            .cachedIn(viewModelScope)
            .map {
                dbMapper.mapSavedPhotoToPhoto(it)
            }
            .asLiveData()

}