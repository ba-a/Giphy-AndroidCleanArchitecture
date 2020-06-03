package de.abauer.giphy_clean_architecture.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import de.abauer.giphy_androidcleanarchitecture.databinding.ItemSearchresultGiphyBinding
import de.abauer.giphy_clean_architecture.data.inject.GlideApp
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import org.jetbrains.annotations.NotNull

interface SearchClickListener {
    fun onSearchItemClick(giphyUrl: String, imageView: ImageView)
}

class SearchAdapter(var searchResultGiphys: List<Giphy>) :
    RecyclerView.Adapter<SearchAdapter.SearchResultGiphysViewHolder>() {

    var searchClickListener: SearchClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultGiphysViewHolder {
        val itemBinding = ItemSearchresultGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultGiphysViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: SearchResultGiphysViewHolder, position: Int) {
        val item = searchResultGiphys[position]
        viewHolder.displayGiphy(item, searchClickListener)
    }

    override fun getItemCount(): Int = searchResultGiphys.size

    class SearchResultGiphysViewHolder(
        itemBinding: @NotNull ItemSearchresultGiphyBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val imageViewArtist: ImageView = itemBinding.imageViewSearchresultGiphy

        fun displayGiphy(item: Giphy, clickListener: SearchClickListener?) {
            GlideApp.with(itemView.context).asGif().load(item.url).into(imageViewArtist)
            imageViewArtist.transitionName = item.url
            itemView.setOnClickListener {
                clickListener?.onSearchItemClick(item.url, imageViewArtist)
            }
        }
    }
}
