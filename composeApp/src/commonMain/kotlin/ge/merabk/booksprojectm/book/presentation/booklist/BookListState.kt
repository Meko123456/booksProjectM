package ge.merabk.booksprojectm.book.presentation.booklist

import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val isLoading: Boolean = true,
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val error: UiText? = null,
    val selectedTabIndex: Int = 0
)
/*
// todo dummy data
private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "qwdsaczx",
        authors = listOf("meraba"),
        description = "TODO()",
        languages = listOf("TODO()"),
        firstPublishedYear = "TODO()",
        averageRating = 54.3,
        ratingCount = 3,
        numPages = 100,
        numEditions = 1,
    )
}*/