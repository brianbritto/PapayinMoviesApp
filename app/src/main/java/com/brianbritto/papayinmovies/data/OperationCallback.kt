package com.brianbritto.papayinmovies.data

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}