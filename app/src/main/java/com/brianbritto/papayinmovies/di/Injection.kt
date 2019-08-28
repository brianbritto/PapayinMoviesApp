package com.brianbritto.papayinmovies.di

import com.brianbritto.papayinmovies.model.MovieDataSource
import com.brianbritto.papayinmovies.model.MovieRepository
import com.brianbritto.papayinmovies.model.VideoDataSource
import com.brianbritto.papayinmovies.model.VideoRepository

object Injection {

    fun providerMovieRepository(): MovieDataSource {
        return MovieRepository()
    }

    fun providerVideoRepository(): VideoDataSource {
        return VideoRepository()
    }
}