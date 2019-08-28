package com.brianbritto.papayinmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brianbritto.papayinmovies.model.VideoDataSource
import com.brianbritto.papayinmovies.model.VideoRepository

class VideoViewModelFactory(private val repository: VideoDataSource): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(repository) as T
    }
}