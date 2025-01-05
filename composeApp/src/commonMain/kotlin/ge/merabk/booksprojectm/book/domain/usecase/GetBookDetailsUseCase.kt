package ge.merabk.booksprojectm.book.domain.usecase

import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError

interface GetBookDetailsUseCase {
    suspend operator fun invoke(
        bookWorkId: String
    ): BooksResult<String, DataError.Remote>
}