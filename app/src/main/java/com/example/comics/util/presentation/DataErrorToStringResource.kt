package com.example.comics.util.presentation

import com.example.comics.R
import com.example.comics.util.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when(this){
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        DataError.Remote.NETWORK -> R.string.error_no_internet
        DataError.Remote.SERVER -> R.string.error_unknown
        DataError.Remote.SERIALIZATION -> R.string.error_serialization
        DataError.Remote.UNKNOWN -> R.string.error_unknown
    }
    return UiText.StringResourceId(stringRes)
}