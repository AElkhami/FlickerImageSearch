package com.elkhami.flickerimagesearch.view.imagesearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import com.elkhami.flickerimagesearch.data.repository.DefaultRepository
import com.elkhami.flickerimagesearch.other.Event
import com.elkhami.flickerimagesearch.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 11,July,2021
 */
@HiltViewModel
class PhotoSearchViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    private val _flickerPhotos = MutableLiveData<Event<Resource<FlickerPhotosResponse>>>()
    var flickerPhotos: LiveData<Event<Resource<FlickerPhotosResponse>>> = _flickerPhotos


    fun searchFlickerWithKeyword(searchWord: String) {
        if(searchWord.isEmpty()){
            return
        }

        _flickerPhotos.value = Event(Resource.Loading())

        viewModelScope.launch {
            val response = repository.searchFlickerWithKeyword(searchWord)
            _flickerPhotos.value = Event(response)
        }
    }

}