package com.samdavid.photobank.utils

data class Resource<out T>(
    val status: Status,
    val message: String?,
    val data: T?
){
    companion object ApiResponseResult {
        fun <T> success(data: T?, message: String?): Resource<T>{
            return Resource(status = Status.SUCCESS, message, data)
        }
        fun <T> error(message: String?): Resource<T>{
            return Resource(status = Status.ERROR, message = message, data = null)
        }
        fun <T> loading(data: T,message: String?): Resource<T>{
            return Resource(status = Status.LOADING, message = null, data = null)
        }
    }
}

sealed class Status{
    object SUCCESS:Status()
    object ERROR:Status()
    object LOADING:Status()
}