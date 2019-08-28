package com.brianbritto.papayinmovies.model

import com.brianbritto.papayinmovies.data.OperationCallback

interface VideoDataSource {
    fun retrieveVideos(movieId: Int, callback: OperationCallback)
    fun cancel()
}