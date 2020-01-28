package com.piyush.itunessearch.data

import com.piyush.itunessearch.data.remote.SearchApi
import com.piyush.itunessearch.model.SearchResultWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl@Inject constructor(var searchApi : SearchApi) : @Inject Repository {

    private var call: Call<SearchResultWrapper>? = null

    override  fun  getSearchResults(string: String, onDataLoadedCallback: OnDataLoadedCallback<SearchResultWrapper>) {
       call =  searchApi.search(string);
       call!!.enqueue(object : Callback<SearchResultWrapper>
        {
            override fun onFailure(call: Call<SearchResultWrapper>, t: Throwable) {
                onDataLoadedCallback.onDataLoadingError(t.message!!)
            }

            override fun onResponse(
                call: Call<SearchResultWrapper>,
                response: Response<SearchResultWrapper>
            ) {
                if(response.isSuccessful && response.code()==200)
                {
                    onDataLoadedCallback.onDataLoaded(response.body()!!)
                }
                else
                {
                    onDataLoadedCallback.onDataLoadingError(response.errorBody().toString())
                }
            }
        })
    }

    override fun cancelCall() {
        if(call!=null && !call!!.isCanceled)
        {
            call!!.cancel()
        }
    }
}