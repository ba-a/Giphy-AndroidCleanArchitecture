package de.giphy_clean_architecture.presentation.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.model.Data
import de.giphy_clean_architecture.data.model.GiphyTrends
import kotlinx.android.synthetic.main.item_trending_giphy.view.*

class GiphyTrendingAdapter(var trendingGiphys: GiphyTrends) :
    RecyclerView.Adapter<GiphyTrendingAdapter.TrendingGiphysViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingGiphysViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_giphy, parent, false)
        return TrendingGiphysViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TrendingGiphysViewHolder, position: Int) {
        val item = trendingGiphys.data[position]
        viewHolder.displayGiphy(item)
    }

    override fun getItemCount(): Int = trendingGiphys.data.size

    class TrendingGiphysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewArtist: ImageView =
            itemView.imageView_trending_giphy

        fun displayGiphy(item: Data) {
            Glide.with(itemView.context).asGif().load(item.images.fixed_height.url).into(imageViewArtist);
        }
    }
}
