package ge.merabk.booksprojectm.book.data.usecase

import ge.merabk.booksprojectm.book.domain.repository.BooksRepository
import ge.merabk.booksprojectm.book.domain.usecase.GetBookDetailsUseCase
import ge.merabk.booksprojectm.core.domain.BooksResult
import ge.merabk.booksprojectm.core.domain.DataError

internal class GetBookDetailsUseCaseImpl(
    private val booksRepository: BooksRepository
) : GetBookDetailsUseCase {
    override suspend fun invoke(bookWorkId: String): BooksResult<String, DataError.Remote> =
        booksRepository.getBookDetails(bookWorkId)
}