package com.elkhami.flickerimagesearch.view.imagesearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import com.elkhami.flickerimagesearch.data.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 11,July,2021
 */
@HiltViewModel
class PhotoSearchViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    private lateinit var _photosFlow: Flow<PagingData<Photo>>
    val photosFlow: Flow<PagingData<Photo>>
        get() = _photosFlow

    fun getPaginatingData(searchWord: String) = viewModelScope.launch {
        val flow = repository.getPaginatingData(searchWord).cachedIn(viewModelScope)
        _photosFlow = flow
    }

}