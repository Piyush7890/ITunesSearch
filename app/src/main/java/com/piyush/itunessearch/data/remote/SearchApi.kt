package com.piyush.itunessearch.data.remote

import com.piyush.itunessearch.model.SearchResultWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

@GET("search")
fun search(@Query("term")term : String) : Call<SearchResultWrapper>

    companion object {
        const val ENDPOINT = "https://itunes.apple.com/"
    }
}