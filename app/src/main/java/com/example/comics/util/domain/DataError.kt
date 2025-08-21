package com.example.comics.util.domain

sealed interface DataError : Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NETWORK,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }
}

interface Error