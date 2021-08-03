package com.elkhami.flickerimagesearch.view.displayphoto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import com.elkhami.flickerimagesearch.data.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 22,July,2021
 */
@HiltViewModel
class DisplayPhotoViewModel
@Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    private val _insertedPhoto = MutableLiveData<SavedPhoto>()
    var insertedPhoto: LiveData<SavedPhoto> = _insertedPhoto

    private val _deletedRowNumber = MutableLiveData<Int>()
    var deletedRowNumber: LiveData<Int> = _deletedRowNumber

    fun savePhoto(savedPhoto: SavedPhoto) {

        savedPhoto.isPhotosSaved = true

        viewModelScope.launch {
            val insertId: Long?
            insertId = repository.insertPhotoToDB(savedPhoto)
            if (insertId > 0) {
                savedPhoto.id = insertId.toInt()
                _insertedPhoto.value = savedPhoto
            }
        }

    }

    fun deletePhoto(photo: SavedPhoto) {
        viewModelScope.launch {
            val deleteId: Int?
            deleteId = repository.deleteSavedPhoto(photo)
            _deletedRowNumber.value = deleteId
        }
    }
}