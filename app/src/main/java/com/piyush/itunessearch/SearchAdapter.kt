package com.piyush.itunessearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.piyush.itunessearch.databinding.ItemSearchResultBinding
import com.piyush.itunessearch.model.SearchResult



class SearchAdapter(radius : Float) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var requestOptions: RequestOptions = RequestOptions()
    var searchResults = emptyList<SearchResult>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    init {
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(radius.toInt()))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil
                .inflate(
                    LayoutInflater
                        .from(parent.context), R.layout.item_search_result, parent, false
                )
        )    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = searchResults[position]
        holder.binding.title.text = result.trackName

        Glide.with(holder.itemView.context)
            .load(result.artworkUrl100)
            .apply(requestOptions)
            .into(holder.binding.imageArtwork)
    }

     inner class ViewHolder( val binding: ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.getRoot())
}