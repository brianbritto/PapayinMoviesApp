package com.brianbritto.papayinmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brianbritto.papayinmovies.data.OperationCallback
import com.brianbritto.papayinmovies.model.MovieDataSource
import com.brianbritto.papayinmovies.model.Video
import com.brianbritto.papayinmovies.model.VideoDataSource
import com.brianbritto.papayinmovies.model.VideoRepository

class VideoViewModel(private val repository: VideoDataSource): ViewModel() {
    private val _videos = MutableLiveData<List<Video>>().apply { value = emptyList() }
    val videos: LiveData<List<Video>> = _videos

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadVideos(movieId: Int){
        _isViewLoading.postValue(true)
        repository.retrieveVideos(movieId,object : OperationCallback{
            override fun onSuccess(obj: Any?) {
                _isViewLoading.postValue(false)

                if (obj != null && obj is List<*>) {
                    if (obj.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _videos.value = obj as List<Video>
                    }
                }
            }

            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(obj)
            }

        })
    }
}