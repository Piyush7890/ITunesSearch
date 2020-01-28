package com.piyush.itunessearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piyush.itunessearch.data.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository : Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != MainViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return MainViewModel(
            repository
        ) as T
    }
}
