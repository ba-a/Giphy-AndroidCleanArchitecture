package de.abauer.giphy_clean_architecture.data.service

import de.abauer.giphy_clean_architecture.domain.model.ErrorModel
import retrofit2.HttpException
import timber.log.Timber

class ApiErrorHandler {
    fun traceErrorException(
        throwable: Throwable?
    ): ErrorModel? {
        Timber.tag(TAG).e(throwable)
        return when (throwable) {
            is HttpException -> {
                ErrorModel(
                    null,
                    throwable.code(),
                    errorType = ErrorModel.ErrorType.FULL_SCREEN
                )
            }
            else -> ErrorModel(
                null,
                0,
                errorType = ErrorModel.ErrorType.FULL_SCREEN
            )
        }
    }

    companion object {
        private const val TAG = "ApiErrorHandle"
    }
}