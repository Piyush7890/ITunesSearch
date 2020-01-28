package com.piyush.itunessearch

import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("debounceSearch")
fun deBounce(editText : View,viewModel : MainViewModel)
{

    val editTextDebounce = EditTextDebounce.create(editText as EditText)
    editTextDebounce.watch(object : EditTextDebounce.DebounceCallback {
        override fun onFinished(result: String) {
            viewModel.cancelSearch()
            viewModel._hideProgress.value = false
            viewModel.getSearchResults(result)
        }

    })
}

@BindingAdapter("visible")
fun showProgress(progressBar : ProgressBar, hide : Boolean)
{
    progressBar.visibility = if(!hide) View.VISIBLE else View.GONE
}