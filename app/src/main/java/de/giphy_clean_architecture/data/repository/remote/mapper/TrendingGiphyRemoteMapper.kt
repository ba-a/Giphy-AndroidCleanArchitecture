package de.giphy_clean_architecture.data.repository.remote.mapper

import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.data.model.Mapper
import de.giphy_clean_architecture.domain.model.Giphy

class TrendingGiphyRemoteMapper : Mapper<GiphyTrends, List<Giphy>> {

    override fun invoke(input: GiphyTrends): List<Giphy> {
        val giphyList = mutableListOf<Giphy>()
        input.data?.forEach {
            giphyList.add(Giphy(
                height = it.images.fixed_height.height.orEmpty(),
                size = it.images.fixed_height.size.orEmpty(),
                url = it.images.fixed_height.url.orEmpty(),
                width = it.images.fixed_height.width.orEmpty()))
        }
        return giphyList
    }
}
