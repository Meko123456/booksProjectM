package ge.merabk.booksprojectm.book.presentation.bookdetails

import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.core.presentation.UiText

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null,
    val error: UiText? = null

)
