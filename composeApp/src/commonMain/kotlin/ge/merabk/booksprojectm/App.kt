package ge.merabk.booksprojectm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ge.merabk.booksprojectm.book.presentation.book_list.BookListScreenRoot
import ge.merabk.booksprojectm.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClicked = {

        }
    )
}