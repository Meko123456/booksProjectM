package ge.merabk.booksprojectm.book.data.usecase

import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.book.domain.repository.BooksRepository
import ge.merabk.booksprojectm.book.domain.usecase.SearchBooksUseCase
import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError

internal class SearchBooksUseCaseImpl(
    private val booksRepository: BooksRepository
) : SearchBooksUseCase {
    override suspend fun invoke(
        query: String,
        resultLimit: Int?
    ): BooksResult<List<Book>, DataError.Remote> = booksRepository.searchBooks(query, resultLimit)
}

