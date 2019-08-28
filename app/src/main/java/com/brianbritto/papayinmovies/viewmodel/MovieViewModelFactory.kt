package com.brianbritto.papayinmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brianbritto.papayinmovies.model.MovieDataSource
import com.brianbritto.papayinmovies.model.MovieRepository

class MovieViewModelFactory(private val repository: MovieDataSource): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}