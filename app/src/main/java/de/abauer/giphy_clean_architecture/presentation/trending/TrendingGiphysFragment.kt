package de.abauer.giphy_clean_architecture.presentation.trending

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.abauer.giphy_androidcleanarchitecture.R
import de.abauer.giphy_androidcleanarchitecture.databinding.GiphyTrendingFragmentBinding
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import de.abauer.giphy_clean_architecture.presentation.MainActivity
import io.uniflow.androidx.flow.onStates
import org.koin.android.viewmodel.ext.android.viewModel
import viewLifecycleLazy


class TrendingGiphysFragment : Fragment(R.layout.giphy_trending_fragment), TrendingClickListener {

    private val trendingGiphysViewModel: TrendingGiphysViewModel by viewModel()
    private lateinit var trendingGiphyAdapter: TrendingGiphyAdapter

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private val binding by viewLifecycleLazy { GiphyTrendingFragmentBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity!! as MainActivity).supportActionBar!!.title = getString(R.string.trendingTitle)

        // Init views
        initRecyclerView()

        // Init state handling
        initStateHandling()

        // Start loading data
        trendingGiphysViewModel.getTrendingGiphys()
    }

    private fun initStateHandling() {
        onStates(trendingGiphysViewModel) { state ->
            when (state) {
                is TrendingGiphysState.Loading -> {
                    showLoading()
                }
                is TrendingGiphysState.ShowSuccess -> {
                    hideLoading()
                    showTrendingGiphys(state.trendingGiphys)
                }
                is TrendingGiphysState.ShowError -> {
                    hideLoading()
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(context, getString(R.string.trending_giphys_error), Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.spinKitTrendingGiphy.visibility = VISIBLE
    }

    private fun hideLoading() {
        binding.spinKitTrendingGiphy.visibility = GONE
    }

    private fun initRecyclerView() {
        trendingGiphyAdapter = TrendingGiphyAdapter((emptyList()))
        trendingGiphyAdapter.clickListener = this
        binding.viewPagerTrendingGiphy.adapter = trendingGiphyAdapter
        binding.viewPagerTrendingGiphy.offscreenPageLimit = 5

        val pageTransformer = TrendingGiphysParallaxPageTransformer()
        binding.viewPagerTrendingGiphy.setPageTransformer(pageTransformer)
    }

    private fun showTrendingGiphys(giphys: List<Giphy>) {
        trendingGiphyAdapter.trendingGiphys = giphys
        trendingGiphyAdapter.notifyDataSetChanged()
    }

    override fun onTrendingItemClick(giphyUrl: String, imageView: ImageView) {
        val action = TrendingGiphysFragmentDirections.actionGiphyTrendingFragmentToGiphyDetailFragment(giphyUrl)
        findNavController().navigate(action)
    }
}
