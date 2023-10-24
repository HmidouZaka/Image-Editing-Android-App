package com.projectbyjanconnect.imageeditor.utils

sealed class Response<T>(
    open val data:T? = null,
    open val message:String? = null
){
    data class Success<T>(override val data: T?) : Response<T>()
    data class Error<T>(override val message: String?) : Response<T>()
    class Loading<T> : Response<T>()
}