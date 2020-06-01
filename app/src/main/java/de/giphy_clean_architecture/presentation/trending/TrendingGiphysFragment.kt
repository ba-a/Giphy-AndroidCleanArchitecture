package de.giphy_clean_architecture.presentation.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.presentation.MainActivity
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.giphy_trending_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class TrendingGiphysFragment : Fragment(), TrendingClickListener {

    private val trendingGiphysViewModel: TrendingGiphysViewModel by viewModel()
    private var trendingGiphyAdapter: TrendingGiphyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.giphy_trending_fragment, container, false)
    }

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
        spin_kit_trendingGiphy.visibility = VISIBLE
    }

    private fun hideLoading() {
        spin_kit_trendingGiphy.visibility = GONE
    }

    private fun initRecyclerView() {
        trendingGiphyAdapter = TrendingGiphyAdapter((emptyList()))
        trendingGiphyAdapter!!.clickListener = this
        viewPager_trending_giphy.adapter = trendingGiphyAdapter
        viewPager_trending_giphy.offscreenPageLimit = 5

        val pageTransformer = TrendingGiphysParallaxPageTransformer()

        viewPager_trending_giphy.setPageTransformer(pageTransformer)
    }

    private fun showTrendingGiphys(giphys: List<Giphy>) {
        trendingGiphyAdapter?.trendingGiphys = giphys
        trendingGiphyAdapter?.notifyDataSetChanged()
    }

    override fun onTrendingItemClick(giphyUrl: String, imageView: ImageView) {
        val action = TrendingGiphysFragmentDirections.actionGiphyTrendingFragmentToGiphyDetailFragment(giphyUrl)
        findNavController().navigate(action)
    }
}
