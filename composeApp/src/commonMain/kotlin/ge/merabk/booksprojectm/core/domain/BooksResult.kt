package ge.merabk.booksprojectm.core.domain

interface Error

sealed interface BooksResult<out D, out E : Error> {
    data class Success<out D>(val data: D) : BooksResult<D, Nothing>
    data class Error<out E : ge.merabk.booksprojectm.core.domain.Error>(val error: E) :
        BooksResult<Nothing, E>
}

inline fun <T, E : Error, R> BooksResult<T, E>.map(map: (T) -> R): BooksResult<R, E> {
    return when (this) {
        is BooksResult.Error -> BooksResult.Error(error)
        is BooksResult.Success -> BooksResult.Success(map(data))
    }
}

fun <T, E : Error> BooksResult<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

inline fun <T, E : Error> BooksResult<T, E>.onSuccess(action: (T) -> Unit): BooksResult<T, E> {
    return when (this) {
        is BooksResult.Error -> this
        is BooksResult.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E : Error> BooksResult<T, E>.onError(action: (E) -> Unit): BooksResult<T, E> {
    return when (this) {
        is BooksResult.Error -> {
            action(error)
            this
        }

        is BooksResult.Success -> this
    }
}

typealias EmptyResult<E> = BooksResult<Unit, E>