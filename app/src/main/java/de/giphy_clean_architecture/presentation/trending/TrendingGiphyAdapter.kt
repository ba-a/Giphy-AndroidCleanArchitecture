package de.giphy_clean_architecture.presentation.trending

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.domain.model.Giphy
import kotlinx.android.synthetic.main.item_trending_giphy.view.*
import java.util.*

class TrendingGiphyAdapter(var trendingGiphys: List<Giphy>) :
    RecyclerView.Adapter<TrendingGiphyAdapter.TrendingGiphysViewHolder>() {

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
        viewHolder.displayGiphy(item)
    }

    override fun getItemCount(): Int = trendingGiphys.size

    class TrendingGiphysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewArtist: ImageView =
            itemView.imageView_trending_giphy

        fun displayGiphy(item: Giphy) {
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            itemView.setBackgroundColor(color)
            Glide.with(itemView.context).asGif().load(item.url).into(imageViewArtist)
        }
    }
}
