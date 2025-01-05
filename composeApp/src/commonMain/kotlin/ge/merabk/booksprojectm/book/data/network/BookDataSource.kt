package ge.merabk.booksprojectm.book.data.network

import ge.merabk.booksprojectm.book.data.dto.SearchResponseDto
import ge.merabk.booksprojectm.core.domain.DataError
import ge.merabk.booksprojectm.core.domain.BooksResult

interface BookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): BooksResult<SearchResponseDto, DataError.Remote>

//    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}