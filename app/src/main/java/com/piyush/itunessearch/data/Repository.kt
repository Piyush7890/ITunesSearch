package com.piyush.itunessearch.data

import com.piyush.itunessearch.model.SearchResultWrapper

interface Repository
{
    fun getSearchResults( string : String,
                          onDataLoadedCallback: OnDataLoadedCallback<SearchResultWrapper>)
    fun cancelCall()
}