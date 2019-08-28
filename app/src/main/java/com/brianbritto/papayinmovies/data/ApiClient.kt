package com.brianbritto.papayinmovies.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiClient {

    private val API_BASE_URL = "https://api.themoviedb.org/3/"

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(): ServicesApiInterface? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {
        @GET("discover/movie")
        fun getMovies(@Query("api_key") apiKey: String = "752cd23fdb3336557bf3d8724e115570"): Call<MovieResponse>

        @GET("movie/{id}/videos")
        fun getVideosByMovieId(@Path("id") movieId: Int, @Query("api_key") apiKey: String = "752cd23fdb3336557bf3d8724e115570"): Call<VideoResponse>

        @GET("movie/{id}")
        fun getMovieDetails(@Path("id") movieId: Int, @Query("api_key") apiKey: String = "752cd23fdb3336557bf3d8724e115570"): Call<MovieDetailsResponse>
    }
}