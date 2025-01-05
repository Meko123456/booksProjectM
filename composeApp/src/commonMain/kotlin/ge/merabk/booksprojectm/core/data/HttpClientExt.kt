package ge.merabk.booksprojectm.core.data

import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): BooksResult<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return BooksResult.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return BooksResult.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return BooksResult.Error(DataError.Remote.UNKNOWN)
    }

    return responseToBooksResult(response)
}

suspend inline fun <reified T> responseToBooksResult(
    response: HttpResponse
): BooksResult<T, DataError.Remote> = when (response.status.value) {
    in 200..299 -> {
        try {
            BooksResult.Success(response.body<T>())
        } catch (e: NoTransformationFoundException) {
            BooksResult.Error(DataError.Remote.SERIALIZATION)
        }
    }

//    404 -> BooksResult.Error(DataError.Remote.NOT_FOUND)
    408 -> BooksResult.Error(DataError.Remote.REQUEST_TIMEOUT)
    429 -> BooksResult.Error(DataError.Remote.TOO_MANY_REQUESTS)
    in 500..599 -> BooksResult.Error(DataError.Remote.SERVER)
    else -> BooksResult.Error(DataError.Remote.UNKNOWN)

}