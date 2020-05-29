package de.giphy_clean_architecture.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.inject.GlideApp
import de.giphy_clean_architecture.domain.model.Giphy
import kotlinx.android.synthetic.main.item_searchresult_giphy.view.*
import kotlinx.android.synthetic.main.item_trending_giphy.view.*

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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_searchresult_giphy, parent, false)
        return SearchResultGiphysViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SearchResultGiphysViewHolder, position: Int) {
        val item = searchResultGiphys[position]
        viewHolder.displayGiphy(item, searchClickListener)
    }

    override fun getItemCount(): Int = searchResultGiphys.size

    class SearchResultGiphysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewArtist: ImageView =
            itemView.imageView_searchresult_giphy

        fun displayGiphy(item: Giphy, clickListener: SearchClickListener?) {
            GlideApp.with(itemView.context).asGif().load(item.url).into(imageViewArtist)
            imageViewArtist.transitionName = item.url
            itemView.setOnClickListener {
                clickListener?.onSearchItemClick(item.url, imageViewArtist)
            }
        }
    }
}
