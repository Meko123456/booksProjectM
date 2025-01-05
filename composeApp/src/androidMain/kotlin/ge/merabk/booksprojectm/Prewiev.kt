package ge.merabk.booksprojectm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.book.presentation.booklist.BookListScreen
import ge.merabk.booksprojectm.book.presentation.booklist.BookListState
import ge.merabk.booksprojectm.book.presentation.booklist.components.BookList
import ge.merabk.booksprojectm.book.presentation.booklist.components.BookListItem
import ge.merabk.booksprojectm.book.presentation.booklist.components.BookSearchBar

@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

private val books = (1..3).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "qwd",
        authors = listOf("Philipp Lackner"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishedYear = null,
        averageRating = 4.67854,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}

@Preview
@Composable
private fun BookListItemPreview() {
    BookListItem(
        book = books.first(),
        onBookClick = {}
    )
}

@Preview
@Composable
private fun BookListPreview() {
    BookList(
        books = books,
        onBookClick = {}
    )
}


@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {}
    )
}