package de.giphy_clean_architecture.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import de.giphy_androidcleanarchitecture.R
import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchGiphyFragment : Fragment() {

    private val searchViewModel: SearchGiphyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch(view)
        initRecyclerView()
        initStateHandling()
    }

    private fun initRecyclerView() {
        recyclerView_searchFragment.setLayoutManager(GridLayoutManager(context, 2))
        recyclerView_searchFragment.adapter = SearchAdapter(emptyList())
    }

    private fun initSearch(view: View) {
        view.editText_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(
                fieldInput: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (fieldInput.length > 2)
                    searchViewModel.onSearchInput(fieldInput.toString())
            }
        })
    }

    private fun initStateHandling() {
        onStates(searchViewModel) { state ->
            when (state) {
                is SearchState.Loading -> {
                    clearAdapter()
                    showLoading()
                }
                is SearchState.ShowSuccess -> {
                    hideLoading()
                    showSearchResult(state.searchResultGiphys)
                }
                is SearchState.ShowError -> {
                    clearAdapter()
                    hideLoading()
                    showError()
                }
            }
        }
    }

    private fun showSearchResult(searchResults: List<Giphy>) {
        (recyclerView_searchFragment.adapter as SearchAdapter).apply {
            searchResultGiphys = searchResults
            notifyDataSetChanged()
        }
    }

    private fun clearAdapter() {
        (recyclerView_searchFragment.adapter as SearchAdapter).apply {
            searchResultGiphys = emptyList()
            notifyDataSetChanged()
        }
    }

    private fun showError() {
        Toast.makeText(context, getString(R.string.trending_giphys_error), Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        spin_kit_search.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        spin_kit_search.visibility = View.GONE
    }
}
