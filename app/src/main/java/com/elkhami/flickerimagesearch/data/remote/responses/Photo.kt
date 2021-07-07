package com.elkhami.flickerimagesearch.data.remote.responses

data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: Any,
    val title: String
)