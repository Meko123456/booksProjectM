package ge.merabk.booksprojectm.book.presentation.book_list.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import booksprojectm.composeapp.generated.resources.Res
import booksprojectm.composeapp.generated.resources.search_hint
import ge.merabk.booksprojectm.core.presentation.DarkBlue
import ge.merabk.booksprojectm.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookSearchBar(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onImeSearch: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(100),
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            cursorColor = DarkBlue,
            focusedBorderColor = SandYellow,
        ),
        placeholder = {
            Text(
                text = stringResource(Res.string.search_hint),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
            )
        }
    )
}
