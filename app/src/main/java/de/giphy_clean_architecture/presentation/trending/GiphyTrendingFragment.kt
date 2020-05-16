package de.giphy_clean_architecture.presentation.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.data.model.GiphyTrends
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.giphy_trending_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class GiphyTrendingFragment : Fragment() {

    private val giphyTrendingViewModel: GiphyTrendingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.giphy_trending_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe incoming states
        onStates(giphyTrendingViewModel) { state ->
            when (state) {
                // react on WeatherState update
                is GiphyTrendingState -> showTrendingGiphys(state)
            }
        }
        initRecyclerView()
        giphyTrendingViewModel.getTrendingGiphys()
    }

    private fun initRecyclerView() {
        recyclerView_trending_giphy.layoutManager = GridLayoutManager(context, 3)
        recyclerView_trending_giphy.adapter = GiphyTrendingAdapter(GiphyTrends(emptyList()))
    }

    private fun showTrendingGiphys(state: GiphyTrendingState) {
        (recyclerView_trending_giphy.adapter as GiphyTrendingAdapter).trendingGiphys =
            state.trendingGiphys
        (recyclerView_trending_giphy.adapter as GiphyTrendingAdapter).notifyDataSetChanged()

    }


}
