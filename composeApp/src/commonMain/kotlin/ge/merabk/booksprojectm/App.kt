package ge.merabk.booksprojectm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ge.merabk.booksprojectm.book.data.network.KtorBookDataSourceImpl
import ge.merabk.booksprojectm.book.data.repository.BooksRepositoryImpl
import ge.merabk.booksprojectm.book.data.usecase.SearchBooksUseCaseImpl
import ge.merabk.booksprojectm.book.presentation.book_list.BookListScreenRoot
import ge.merabk.booksprojectm.book.presentation.book_list.BookListViewModel
import ge.merabk.booksprojectm.core.data.HttpClientFactory.create
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                searchBooksUseCase = SearchBooksUseCaseImpl(
                    booksRepository = BooksRepositoryImpl(
                        bookDataSource = KtorBookDataSourceImpl(
                            httpClient = create(
                                engine = engine
                            )
                        )
                    )
                )
            )
        },
        onBookClicked = {

        }
    )
}