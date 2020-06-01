package de.giphy_clean_architecture.presentation.trending

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.inject.GlideApp
import de.giphy_clean_architecture.domain.model.Giphy
import kotlinx.android.synthetic.main.item_trending_giphy.view.*
import java.util.*

interface TrendingClickListener {
    fun onTrendingItemClick(giphyUrl: String, imageView: ImageView)
}

class TrendingGiphyAdapter(var trendingGiphys: List<Giphy>) :
    RecyclerView.Adapter<TrendingGiphyAdapter.TrendingGiphysViewHolder>() {

    var clickListener: TrendingClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingGiphysViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_giphy, parent, false)
        return TrendingGiphysViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TrendingGiphysViewHolder, position: Int) {
        val item = trendingGiphys[position]
        viewHolder.displayGiphy(item, clickListener)
    }

    override fun getItemCount(): Int = trendingGiphys.size

    class TrendingGiphysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewArtist: ImageView =
            itemView.imageView_trending_giphy

        fun displayGiphy(item: Giphy, clickListener: TrendingClickListener?) {
            val rnd = Random()
            itemView.imageView_trending_giphy.translationX = 0f
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            itemView.setBackgroundColor(color)
            GlideApp.with(itemView.context).asGif().load(item.url).into(imageViewArtist)
            itemView.setOnClickListener {
                clickListener?.onTrendingItemClick(item.url, imageViewArtist)
            }
        }
    }
}
