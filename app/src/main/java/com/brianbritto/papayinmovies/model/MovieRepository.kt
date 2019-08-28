package com.brianbritto.papayinmovies.model

import android.util.Log
import com.brianbritto.papayinmovies.data.ApiClient
import com.brianbritto.papayinmovies.data.MovieDetailsResponse
import com.brianbritto.papayinmovies.data.MovieResponse
import com.brianbritto.papayinmovies.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository : MovieDataSource {

    companion object {
        private val TAG = MovieRepository::class.java.name
    }

    private var callMovie: Call<MovieResponse>? = null
    private var callMovieDetails: Call<MovieDetailsResponse>? = null

    override fun retrieveMovies(callback: OperationCallback) {
        callMovie = ApiClient.build()?.getMovies()
        callMovie?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.v(TAG, "data ${it.results}")
                        callback.onSuccess(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    override fun retrieveMovieDetails(movieId: Int, callback: OperationCallback) {
        callMovieDetails = ApiClient.build()?.getMovieDetails(movieId)
        callMovieDetails?.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(call: Call<MovieDetailsResponse>, response: Response<MovieDetailsResponse>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.v(TAG, "data ${it}")
                        callback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    override fun cancel() {
        callMovie?.cancel()
        callMovieDetails?.cancel()
    }

}