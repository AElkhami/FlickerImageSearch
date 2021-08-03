package com.elkhami.flickerimagesearch.view.savedphotos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by A.Elkhami on 23,July,2021
 */
@HiltViewModel
class SavedPhotosViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    val getSavedPhotos: LiveData<PagingData<SavedPhoto>> =
        repository
            .getAllSavedPhotos()
            .cachedIn(viewModelScope)
            .asLiveData()

}