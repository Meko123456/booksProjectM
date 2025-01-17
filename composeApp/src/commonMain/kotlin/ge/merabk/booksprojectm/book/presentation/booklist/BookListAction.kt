package ge.merabk.booksprojectm.book.presentation.booklist

import ge.merabk.booksprojectm.book.domain.model.Book

sealed interface BookListAction {

    data class OnSearchQueryChange(val query: String) : BookListAction

    data class OnBookClick(val book: Book) : BookListAction

    data class OnTabSelected(val index: Int) : BookListAction
}