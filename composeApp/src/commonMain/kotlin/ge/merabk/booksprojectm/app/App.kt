package ge.merabk.booksprojectm.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ge.merabk.booksprojectm.book.presentation.booklist.BookListScreenRoot
import ge.merabk.booksprojectm.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.BookGraph
    ) {
        navigation<Route.BookGraph>(startDestination = Route.BookList) {
            composable<Route.BookList> {
                val viewModel = koinViewModel<BookListViewModel>()
                BookListScreenRoot(
                    viewModel = remember {
                        viewModel
                    },
                    onBookClicked = { book ->
                        navController.navigate(Route.BookDetail(book.id))

                    }
                )
            }
            composable<Route.BookDetail>() { entry ->
                val args = entry.toRoute<Route.BookDetail>()
                args.id

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Book Details screen is " + args.id)
                }
            }


        }
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