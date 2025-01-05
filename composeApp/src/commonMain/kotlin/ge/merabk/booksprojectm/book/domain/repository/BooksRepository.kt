package ge.merabk.booksprojectm.book.domain.repository

import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError

interface BooksRepository {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): BooksResult<List<Book>, DataError.Remote>

//    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}