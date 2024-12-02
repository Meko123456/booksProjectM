package ge.merabk.booksprojectm.book.presentation.book_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import booksprojectm.composeapp.generated.resources.Res
import booksprojectm.composeapp.generated.resources.book_error_2
import coil3.compose.rememberAsyncImagePainter
import ge.merabk.booksprojectm.book.domain.Book
import ge.merabk.booksprojectm.core.presentation.LightBlue
import ge.merabk.booksprojectm.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round


@Composable
fun BookListItem(
    modifier: Modifier = Modifier,
    book: Book,
    onBookClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(onClick = onBookClick),
        color = LightBlue.copy(alpha = 0.2f)
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {

                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(null)
                }

                val painter = rememberAsyncImagePainter(
                    model = book.imageUrl,
                    onSuccess = {
                        val isImageUrlValid = it.painter.intrinsicSize.width > 1
                                && it.painter.intrinsicSize.height > 1

                        if (isImageUrlValid) {
                            Result.success(it)
                        } else {
                            Result.failure(Exception("Invalid image size"))
                        }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)

                    }
                )


                when (val result = imageLoadResult) {
                    null -> CircularProgressIndicator()
                    else -> Image(
                        painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                        contentDescription = book.title,
                        contentScale = if (result.isSuccess) ContentScale.Crop
                        else ContentScale.Fit,
                        modifier = modifier.aspectRatio(
                            ratio = 0.65f,
                            matchHeightConstraintsFirst = true
                        )
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                book.authors.firstOrNull()?.let { authorName ->
                    Text(
                        text = authorName,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                book.averageRating?.let { rating ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = round(rating * 10 / 10).toString(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = SandYellow
                        )
                    }

                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.size(36.dp)
            )
        }
    }
}
