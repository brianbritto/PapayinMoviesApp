package com.brianbritto.papayinmovies.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("genre_ids") val genreIds: List<Int?>?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?
) : Serializable

data class Video(
    @SerializedName("id") val id: String?,
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("iso_3166_1") val iso_3166_1: String?,
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("site") val site: String?,
    @SerializedName("size") val size: Int?,
    @SerializedName("type") val type: String?
) : Serializable

data class Genre(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
) : Serializable

data class SpokenLanguage(
    @SerializedName("iso_639_1") val iso_639_1: String?,
    @SerializedName("name") val name: String?
) : Serializable

data class ProductionCompany (
    @SerializedName("id") val id : Int?,
    @SerializedName("logo_path") val logoPath : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("origin_country") val originCountry : String?
): Serializable

data class ProductionCountry (
    @SerializedName("iso_3166_1") val iso_3166_1 : String?,
    @SerializedName("name") val name : String?
): Serializable