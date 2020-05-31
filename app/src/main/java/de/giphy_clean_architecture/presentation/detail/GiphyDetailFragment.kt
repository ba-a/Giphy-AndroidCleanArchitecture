package de.giphy_clean_architecture.presentation.detail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.inject.GlideApp
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.fragment_giphy_detail.*
import org.koin.android.ext.android.inject


class GiphyDetailFragment : Fragment() {

    private val giphyDetailFragmentArgs: GiphyDetailFragmentArgs by navArgs()
    private val giphyDetailViewModel: GiphyDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_giphy_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStateHandling()

        giphyDetailViewModel.onGiphyReceived(giphyDetailFragmentArgs.giphyUrl)

        fab_detail.setOnClickListener {
            giphyDetailViewModel.onShareButtonClick(giphyDetailFragmentArgs.giphyUrl)
        }
    }

    private fun initStateHandling() {
        onStates(giphyDetailViewModel) { state ->
            when (state) {
                is GiphyDetailState.LoadGiphy -> {
                    loadGiphy(state.giphyUrl)
                }
                is GiphyDetailState.ShareGiphy -> {
                    createShareChooser(state.giphyUrl)
                }
            }
        }
    }

    private fun loadGiphy(url: String) {
        context?.let {
            GlideApp.with(it).asGif().load(url).into(imageView_detail_giphy)
        }
    }

    private fun createShareChooser(url: String) {
        ShareCompat.IntentBuilder.from(activity as Activity)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_giphy))
            .setText(url)
            .startChooser()
    }
}