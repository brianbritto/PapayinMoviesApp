package com.brianbritto.papayinmovies.model

import android.util.Log
import com.brianbritto.papayinmovies.data.ApiClient
import com.brianbritto.papayinmovies.data.OperationCallback
import com.brianbritto.papayinmovies.data.VideoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository : VideoDataSource {

    companion object{
        private val TAG = VideoRepository::class.java.name
    }

    private var call: Call<VideoResponse>? = null

    override fun retrieveVideos(movieId: Int,callback: OperationCallback) {
        call= ApiClient.build()?.getVideosByMovieId(movieId)
        call?.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                response.body()?.let {
                    if(response.isSuccessful) {
                        Log.v(TAG, "data ${it.results}")
                        callback.onSuccess(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    override fun cancel() {
        call?.cancel()
    }
}