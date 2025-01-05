package ge.merabk.booksprojectm.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResponseDto(
    @SerialName("docs") val results: List<BookDto>
)