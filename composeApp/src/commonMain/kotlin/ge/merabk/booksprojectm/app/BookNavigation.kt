package ge.merabk.booksprojectm.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ge.merabk.booksprojectm.book.presentation.SelectedBookViewModel
import ge.merabk.booksprojectm.book.presentation.bookdetails.BookDetailAction
import ge.merabk.booksprojectm.book.presentation.bookdetails.BookDetailScreenRoot
import ge.merabk.booksprojectm.book.presentation.bookdetails.BookDetailViewModel
import ge.merabk.booksprojectm.book.presentation.booklist.BookListScreenRoot
import ge.merabk.booksprojectm.book.presentation.booklist.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.bookGraph(navController: NavHostController) {
    navigation<Route.BookGraph>(startDestination = Route.BookList) {
        composableBookList(navController)
        composableBookDetail(navController)
    }
}

fun NavGraphBuilder.composableBookList(navController: NavHostController) {
    composable<Route.BookList>(
        exitTransition = { slideOutHorizontally() },
        popEnterTransition = { slideInHorizontally() }
    ) { navBackStackEntry ->
        val viewModel = koinViewModel<BookListViewModel>()
        val selectedBookViewModel =
            navBackStackEntry.sharedKoinViewModel<SelectedBookViewModel>(navController)

        LaunchedEffect(true) {
            selectedBookViewModel.onSelectBook(null)
        }

        BookListScreenRoot(
            viewModel = remember { viewModel },
            onBookClicked = { book ->
                selectedBookViewModel.onSelectBook(book)
                navController.navigate(Route.BookDetail(book.id))
            }
        )
    }
}

fun NavGraphBuilder.composableBookDetail(navController: NavHostController) {
    composable<Route.BookDetail>(
        enterTransition = {
            slideInHorizontally { initialOffset ->
                initialOffset
            }
        },
        exitTransition = {
            slideOutHorizontally { initialOffset ->
                initialOffset
            }
        }
    ) {
        val selectedBookViewModel =
            it.sharedKoinViewModel<SelectedBookViewModel>(navController)
        val viewModel = koinViewModel<BookDetailViewModel>()
        val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

        LaunchedEffect(selectedBook) {
            selectedBook?.let {
                viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
            }
        }

        BookDetailScreenRoot(
            viewModel = viewModel,
            onBackClick = {
                navController.navigateUp()
            }
        )
    }
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}