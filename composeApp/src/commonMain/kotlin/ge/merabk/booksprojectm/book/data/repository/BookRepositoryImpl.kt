package ge.merabk.booksprojectm.book.data.repository

import ge.merabk.booksprojectm.book.data.network.BookDataSource
import ge.merabk.booksprojectm.book.data.toBook
import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.book.domain.repository.BooksRepository
import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError
import ge.merabk.booksprojectm.core.domain.map

class BooksRepositoryImpl(
    private val bookDataSource: BookDataSource
) : BooksRepository {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): BooksResult<List<Book>, DataError.Remote> =
        bookDataSource.searchBooks(query, resultLimit).map {
            it.results.map { bookDto -> bookDto.toBook() }
        }

}