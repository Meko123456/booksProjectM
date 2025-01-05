package ge.merabk.booksprojectm.core.domain

sealed interface DataError : Error {
    enum class Remote : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
//        NOT_FOUND,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}