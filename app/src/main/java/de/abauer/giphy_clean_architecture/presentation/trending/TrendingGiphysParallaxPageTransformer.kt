package de.abauer.giphy_clean_architecture.presentation.trending

import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import de.abauer.giphy_androidcleanarchitecture.R

class TrendingGiphysParallaxPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth: Int = view.width
        when {
            position < -1 -> { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 1f
            }
            position <= 1 -> { // [-1,1]
                val imageViewTrending = view.findViewById<ImageView>(R.id.imageView_trending_giphy)
                val giphy = imageViewTrending
                giphy.translationX = -position * (pageWidth / 2) //Half the normal speed
            }
            else -> { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 1f
            }
        }
    }
}