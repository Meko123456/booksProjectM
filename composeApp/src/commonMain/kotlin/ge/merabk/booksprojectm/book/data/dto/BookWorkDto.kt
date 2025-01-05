package ge.merabk.booksprojectm.book.data.dto

import kotlinx.serialization.Serializable

@Serializable(with = BookWorkDtoSerializer::class)
internal data class BookWorkDto(
    val description: String? = null
)

