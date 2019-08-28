package com.brianbritto.papayinmovies.model

import com.brianbritto.papayinmovies.data.OperationCallback

interface MovieDataSource {
    fun retrieveMovies(callback: OperationCallback)
    fun retrieveMovieDetails(movieId: Int, callback: OperationCallback)
    fun cancel()
}