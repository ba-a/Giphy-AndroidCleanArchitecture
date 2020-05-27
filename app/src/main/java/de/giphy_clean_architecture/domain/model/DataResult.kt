package de.giphy_clean_architecture.domain.model

sealed class DataResult<out T : Any> {
    data class Success<out T : Any>(val value: T) : DataResult<T>()
    data class Error(val errorModel: ErrorModel?) : DataResult<Nothing>()
}

data class ErrorModel(
    val message: String?,
    val code: Int?,
    val exception: Throwable? = null,
    val errorType: ErrorType? = null
) {
    enum class ErrorType {
        FULL_SCREEN,
        HINT,
        IGNORE
    }
}