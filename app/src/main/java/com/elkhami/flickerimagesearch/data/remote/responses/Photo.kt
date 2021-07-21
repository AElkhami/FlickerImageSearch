package com.elkhami.flickerimagesearch.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Photo(
    val farm: Int? = null,
    val id: String? = null,
    val isfamily: Int? = null,
    val isfriend: Int? = null,
    val ispublic: Int? = null,
    val owner: String? = null,
    val secret: String? = null,
    val server: String? = null,
    val title: String? = null,
    var photoURL: String? = null
): Parcelable