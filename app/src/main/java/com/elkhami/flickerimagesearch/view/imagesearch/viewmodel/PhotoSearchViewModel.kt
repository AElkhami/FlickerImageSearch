package com.elkhami.flickerimagesearch.view.imagesearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.flickerimagesearch.data.remote.responses.FlickerPhotosResponse
import com.elkhami.flickerimagesearch.data.repository.DefaultRepository
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

     fun searchFlickerWithKeyword(searchWord: String) {
         viewModelScope.launch {
             val response = repository.searchFlickerWithKeyword(searchWord)
         }
    }

}