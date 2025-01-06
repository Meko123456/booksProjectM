package ge.merabk.booksprojectm.book.presentation.bookdetails

import ge.merabk.booksprojectm.book.domain.model.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}