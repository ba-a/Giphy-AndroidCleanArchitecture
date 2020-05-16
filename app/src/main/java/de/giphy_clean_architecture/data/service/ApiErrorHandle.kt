package de.giphy_clean_architecture.data.service

import de.giphy_clean_architecture.domain.model.ErrorModel
import timber.log.Timber

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns a [ErrorModel]
 *
 */
class ApiErrorHandle {
    private val TAG = "ApiErrorHandle"

    fun traceErrorException(
        throwable: Throwable?
    ): ErrorModel? {
        Timber.tag(TAG).e(throwable)

        return ErrorModel(
            null,
            0,
            errorType = ErrorModel.ErrorType.FULL_SCREEN
        )
    }
}