package com.elkhami.flickerimagesearch.other

/**
 * Created by A.Elkhami on 11,July,2021
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Status
){
    class Success<T>(data: T?): Resource<T>(data = data, status = Status.SUCCESS)
    class Failed<T>(data: T? = null, message: String?): Resource<T>(data, message, Status.FAILED)
    class Loading<T>(): Resource<T>(status = Status.LOADING)

}

enum class Status{
    SUCCESS,
    FAILED,
    LOADING
}
