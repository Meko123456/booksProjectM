package ge.merabk.booksprojectm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ge.merabk.booksprojectm.book.presentation.book_list.components.BookSearchBar


@Composable
@Preview
private fun PreviewBookSearchBar() {
    BookSearchBar(
        searchQuery = "Kotlin",
        onSearchQueryChange = {},
        onImeSearch = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
}