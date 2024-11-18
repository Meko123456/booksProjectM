package ge.merabk.booksprojectm.book.presentation.book_list

import ge.merabk.booksprojectm.book.domain.Book
import ge.merabk.booksprojectm.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val isLoading: Boolean = false,
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val error: UiText? = null,
    val selectedTabIndex: Int = 0
)
