package com.chetan.movietime.data.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.chetan.movietime.data.APIResponse
import com.chetan.movietime.data.Movie
import com.chetan.movietime.data.network.MovieService
import com.chetan.movietime.data.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Chetan on 2020-03-05.
 */

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {


    private var movieService: MovieService = RetrofitFactory.makeRetrofitService()

    private var networkState: MutableLiveData<NetworkStatus> = MutableLiveData()

    private var initialLoading: MutableLiveData<NetworkStatus> = MutableLiveData()


    fun getNetworkState() = networkState

    fun isInitialLoading() = initialLoading

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkStatus(Status.LOADING))
        initialLoading.postValue(NetworkStatus(Status.LOADING))

        movieService.getMovies(1).enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {

                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, null, 2)
                    initialLoading.postValue(NetworkStatus(Status.LOADED))
                    networkState.postValue(NetworkStatus(Status.LOADED))

                } else {
                    initialLoading.postValue(NetworkStatus(Status.FAILED, response.message()))
                    networkState.postValue(NetworkStatus(Status.FAILED, response.message()))

                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable?) {
                val errorMessage = if (t == null) "unknown error" else t.message
                networkState.postValue(NetworkStatus(Status.FAILED, errorMessage))

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        if (params.key != -1) {

            networkState.postValue(NetworkStatus(Status.LOADING))

            movieService.getMovies(params.key).enqueue(object : Callback<APIResponse> {
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {

                    if (response.isSuccessful) {

                        var nextPage = -1

                        if (response.body()!!.totalPages > params.key) {
                            nextPage = params.key + 1
                        }

                        callback.onResult(response.body()!!.results, nextPage)
                        initialLoading.postValue(NetworkStatus(Status.LOADED))
                        networkState.postValue(NetworkStatus(Status.LOADED))

                    } else {
                        initialLoading.postValue(NetworkStatus(Status.FAILED, response.message()))
                        networkState.postValue(NetworkStatus(Status.FAILED, response.message()))

                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable?) {
                    val errorMessage = if (t == null) "unknown error" else t.message
                    networkState.postValue(NetworkStatus(Status.FAILED, errorMessage))
                }
            })
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }
}