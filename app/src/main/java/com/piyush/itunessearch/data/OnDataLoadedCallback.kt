package com.piyush.itunessearch.data

interface OnDataLoadedCallback<T> {
    fun onDataLoaded(data: T)
    fun onDataLoadingError(message : String)
}