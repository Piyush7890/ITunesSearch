package com.piyush.itunessearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piyush.itunessearch.data.OnDataLoadedCallback
import com.piyush.itunessearch.data.Repository
import com.piyush.itunessearch.model.SearchResult
import com.piyush.itunessearch.model.SearchResultWrapper

class MainViewModel(val repository: Repository) : ViewModel()

{
    var _hideProgress = MutableLiveData<Boolean>()
    val hideProgress: LiveData<Boolean>
        get() = _hideProgress


    var _results = MutableLiveData<List<SearchResult>>()

    val results: LiveData<List<SearchResult>>
        get() = _results

    var _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val onDataLoadedCallback = object : OnDataLoadedCallback<SearchResultWrapper>
    {
        override fun onDataLoaded(data: SearchResultWrapper) {
            _results.value = data.results
        }

        override fun onDataLoadingError(message: String) {
            _error.value = message
        }

    }

    fun getSearchResults(term : String)
    {
        repository.getSearchResults(term, onDataLoadedCallback)
    }

    fun cancelSearch()
    {
        repository.cancelCall()
    }
}