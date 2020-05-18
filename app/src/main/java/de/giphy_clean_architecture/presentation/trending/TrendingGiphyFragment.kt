package de.giphy_clean_architecture.presentation.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.giphy_trending_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class TrendingGiphyFragment : Fragment() {

    private val trendingGiphyViewModel: TrendingGiphyViewModel by viewModel()

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
                is TrendingGiphyState.ShowLoading -> showLoading()
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
        recyclerView_trending_giphy.layoutManager = GridLayoutManager(context, 3)
        recyclerView_trending_giphy.adapter = TrendingGiphyAdapter(emptyList())
    }

    private fun showTrendingGiphys(giphys: List<Giphy>) {
        (recyclerView_trending_giphy.adapter as TrendingGiphyAdapter).trendingGiphys = giphys
        (recyclerView_trending_giphy.adapter as TrendingGiphyAdapter).notifyDataSetChanged()
    }
}
