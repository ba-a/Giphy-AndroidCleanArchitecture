package de.giphy_androidcleanarchitecture.data.repository.remote.mapper

import com.google.common.truth.Truth.assertThat
import de.giphy_clean_architecture.data.model.Data
import de.giphy_clean_architecture.data.model.FixedHeight
import de.giphy_clean_architecture.data.model.GiphyResultList
import de.giphy_clean_architecture.data.repository.remote.mapper.GiphyRemoteMapper
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Before
import org.junit.Test

class GiphyRemoteMapperTest {

    private lateinit var giphyRemoteMapper: GiphyRemoteMapper

    @Before
    fun setup() {
        giphyRemoteMapper = GiphyRemoteMapper()
    }

    @Test
    fun `invoke Should return list of giphys`() {
        val data = mockkClass(Data::class)
        val input = mockkClass(GiphyResultList::class)

        every { data.images!!.fixed_height } returns FixedHeight("300", "3mb", "someUrl.com", "300")
        every { input.data } returns listOf(data)

        val result = giphyRemoteMapper(input)

        assertThat(result[0].url).isEqualTo("someUrl.com")
    }

    @Test
    fun `invoke Should return empty list for no data`() {
        val input = mockkClass(GiphyResultList::class)
        every { input.data } returns null

        val result = giphyRemoteMapper(input)

        assertThat(result).isEmpty()
    }

}