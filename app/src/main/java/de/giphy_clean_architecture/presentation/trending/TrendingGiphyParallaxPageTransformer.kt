package de.giphy_clean_architecture.presentation.trending

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.item_trending_giphy.*
import kotlinx.android.synthetic.main.item_trending_giphy.view.*
import kotlinx.android.synthetic.main.item_trending_giphy.view.imageView_trending_giphy


class TrendingGiphyParallaxPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth: Int = view.width
        when {
            position < -1 -> { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 1f
            }
            position <= 1 -> { // [-1,1]
                val giphy = view.imageView_trending_giphy
                giphy.translationX = -position * (pageWidth / 2) //Half the normal speed
            }
            else -> { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 1f
            }
        }
    }
}