package de.giphy_clean_architecture.presentation.detail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.transition.Transition
import android.transition.TransitionInflater
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.inject.GlideApp
import kotlinx.android.synthetic.main.fragment_giphy_detail.*
import timber.log.Timber


class GiphyDetailFragment : Fragment() {

    private val giphyDetailFragmentArgs: GiphyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_giphy_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val giphyUrl= giphyDetailFragmentArgs.giphyUrl
        GlideApp.with(context!!).asGif().load(giphyUrl).into(imageView_detail_giphy)

        fab_detail.setOnClickListener {
            ShareCompat.IntentBuilder.from(activity as Activity)
                .setType("text/plain")
                .setChooserTitle("Smyle")
                .setText(giphyUrl)
                .startChooser()
        }
    }
}