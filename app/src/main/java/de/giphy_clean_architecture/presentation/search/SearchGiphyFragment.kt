package de.giphy_clean_architecture.presentation.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.giphy_androidcleanarchitecture.R

class SearchGiphyFragment : Fragment() {

    private lateinit var giphyViewModel: SearchGiphyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        giphyViewModel = ViewModelProviders.of(this).get(SearchGiphyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
