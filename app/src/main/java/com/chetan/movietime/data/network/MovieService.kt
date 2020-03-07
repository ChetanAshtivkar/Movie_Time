package com.chetan.movietime.data.network

import com.chetan.movietime.data.APIResponse
import com.chetan.movietime.data.Movie
import com.chetan.movietime.data.network.RetrofitFactory.API_KEY
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Chetan on 2020-03-05.
 */
interface MovieService {

    @GET("3/movie/top_rated?api_key=$API_KEY&language=en-US")
    fun getMovies(
        @Query("page") pageNumber: Int
    ): Call<APIResponse>

    @GET("3/movie/{movieId}?api_key=$API_KEY&language=en-US")
    fun getMovieById(
        @Path("movieId") movieId: Int
    ): Call<Movie>
}

object RetrofitFactory {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "6af32a306f65cc26cf8c1bc32bf35b5e"

    fun makeRetrofitService(): MovieService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(MovieService::class.java)
    }
}