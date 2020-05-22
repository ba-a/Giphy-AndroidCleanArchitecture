package de.giphy_clean_architecture.presentation.trending

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.item_trending_giphy.view.*


class ParallaxPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth: Int = view.getWidth()
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1f)
        } else if (position <= 1) { // [-1,1]
            val giphy = view.imageView_trending_giphy
            giphy.setTranslationX(-position * (pageWidth / 2)) //Half the normal speed
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1f)
        }
    }
}