package de.abauer.giphy_clean_architecture.data.repository.remote.mapper

import de.abauer.giphy_clean_architecture.data.model.GiphyResultList
import de.abauer.giphy_clean_architecture.data.model.Mapper
import de.abauer.giphy_clean_architecture.domain.model.Giphy

class GiphyRemoteMapper : Mapper<GiphyResultList, List<Giphy>> {

    override fun invoke(input: GiphyResultList): List<Giphy> {
        val giphyList = mutableListOf<Giphy>()
        input.data?.forEach {
            giphyList.add(Giphy(
                height = it.images?.fixed_height?.height.orEmpty(),
                size = it.images?.fixed_height?.size.orEmpty(),
                url = it.images?.fixed_height?.url.orEmpty(),
                width = it.images?.fixed_height?.width.orEmpty()))
        }
        return giphyList
    }
}
