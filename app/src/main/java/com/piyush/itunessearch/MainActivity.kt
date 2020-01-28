package com.piyush.itunessearch

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.piyush.itunessearch.databinding.ActivityMainBinding
import com.piyush.itunessearch.model.SearchResult
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    @Inject
    internal lateinit var viewModelFactory : ViewModelFactory

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.hideProgress = true
        (application as ITunesSearch).appComponent.inject(this)
        val adapter = SearchAdapter(resources.displayMetrics.density*8)
        activityMainBinding.rviewSearchResults.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java);
        activityMainBinding.viewModel = viewModel
        viewModel.apply {
            results.observe(this@MainActivity,
                Observer<List<SearchResult>> {
                    adapter.searchResults = it
                    viewModel._hideProgress.value = true
                })
            hideProgress.observe(this@MainActivity, Observer<Boolean>{
                activityMainBinding.hideProgress = it
            })
        }

        val view = findViewById<View>(android.R.id.content)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.WHITE
        var flags = view.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        view.systemUiVisibility = flags

    }
}
