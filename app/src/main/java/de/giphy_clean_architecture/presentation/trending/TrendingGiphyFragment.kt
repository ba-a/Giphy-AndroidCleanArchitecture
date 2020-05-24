package de.giphy_clean_architecture.presentation.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.giphy_trending_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class TrendingGiphyFragment : Fragment() {

    private val trendingGiphyViewModel: TrendingGiphyViewModel by viewModel()
    private var trendingGiphyAdapter: TrendingGiphyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.giphy_trending_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init views
        initRecyclerView()

        // Init state handling
        initStateHandling()

        // Start loading data
        trendingGiphyViewModel.getTrendingGiphys()
    }

    private fun initStateHandling() {
        onStates(trendingGiphyViewModel) { state ->
            when (state) {
                is TrendingGiphyState.Loading -> {
                    showLoading()
                }
                is TrendingGiphyState.ShowSuccess -> {
                    hideLoading()
                    showTrendingGiphys(state.trendingGiphys)
                }
                is TrendingGiphyState.ShowError -> {
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
        viewPager_trending_giphy.adapter = trendingGiphyAdapter
        viewPager_trending_giphy.offscreenPageLimit = 5
        // Create an object of page transformer
        val pageTransformer = TrendingGiphyParallaxPageTransformer()
        viewPager_trending_giphy.setPageTransformer(pageTransformer)
    }

    private fun showTrendingGiphys(giphys: List<Giphy>) {
        trendingGiphyAdapter?.trendingGiphys = giphys
        trendingGiphyAdapter?.notifyDataSetChanged()
    }
}
