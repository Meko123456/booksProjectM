package ge.merabk.booksprojectm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.merabk.booksprojectm.book.domain.Book
import ge.merabk.booksprojectm.book.presentation.book_list.BookListScreen
import ge.merabk.booksprojectm.book.presentation.book_list.BookListState
import ge.merabk.booksprojectm.book.presentation.book_list.components.BookSearchBar


@Composable
@Preview(showBackground = true)
private fun PreviewBookSearchBar() {
    MaterialTheme {

        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

    }
}

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
}

@Composable
@Preview(showBackground = true)
private fun PreviewBookListScreen() {

    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {

        })
}