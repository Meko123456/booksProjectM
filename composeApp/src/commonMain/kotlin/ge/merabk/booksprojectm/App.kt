package ge.merabk.booksprojectm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ge.merabk.booksprojectm.book.presentation.booklist.BookListScreenRoot
import ge.merabk.booksprojectm.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = remember {
            viewModel
        },
        onBookClicked = {

        }
    )
}