package ge.merabk.booksprojectm.book.domain.usecase

import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError

interface SearchBooksUseCase {
    suspend operator fun invoke(
        query: String,
        resultLimit: Int? = null
    ): BooksResult<List<Book>, DataError.Remote>
}