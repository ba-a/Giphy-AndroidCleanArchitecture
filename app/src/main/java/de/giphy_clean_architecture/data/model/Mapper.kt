package de.giphy_clean_architecture.data.model

interface Mapper<in I, out O> {
    operator fun invoke(input: I): O
}