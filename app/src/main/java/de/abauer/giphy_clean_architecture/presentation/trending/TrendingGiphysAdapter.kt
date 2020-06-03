package de.abauer.giphy_clean_architecture.presentation.trending

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import de.abauer.giphy_androidcleanarchitecture.databinding.ItemTrendingGiphyBinding
import de.abauer.giphy_clean_architecture.data.inject.GlideApp
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import org.jetbrains.annotations.NotNull
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

        val itemBinding = ItemTrendingGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrendingGiphysViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: TrendingGiphysViewHolder, position: Int) {
        val item = trendingGiphys[position]
        viewHolder.displayGiphy(item, clickListener)
    }

    override fun getItemCount(): Int = trendingGiphys.size

    class TrendingGiphysViewHolder(itemBinding: @NotNull ItemTrendingGiphyBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private val imageViewArtist: ImageView =
            itemBinding.imageViewTrendingGiphy

        fun displayGiphy(item: Giphy, clickListener: TrendingClickListener?) {
            val rnd = Random()
            imageViewArtist.translationX = 0f
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            itemView.setBackgroundColor(color)
            GlideApp.with(itemView.context).asGif().load(item.url).into(imageViewArtist)
            itemView.setOnClickListener {
                clickListener?.onTrendingItemClick(item.url, imageViewArtist)
            }
        }
    }
}
