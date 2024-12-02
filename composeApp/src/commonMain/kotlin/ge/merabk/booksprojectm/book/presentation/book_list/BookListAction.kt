package ge.merabk.booksprojectm.book.presentation.book_list

import ge.merabk.booksprojectm.book.domain.Book

sealed interface BookListAction {

    data class OnSearchQueryChange(val query: String) : BookListAction

    data class OnBookClick(val book: Book) : BookListAction

    data class OnTabSelected(val index: Int) : BookListAction
}