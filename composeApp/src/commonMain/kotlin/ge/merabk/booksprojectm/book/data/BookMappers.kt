package ge.merabk.booksprojectm.book.data

import ge.merabk.booksprojectm.book.data.dto.BookDto
import ge.merabk.booksprojectm.book.domain.model.Book


internal fun BookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames.orEmpty(),
        description = null,
        languages = languages.orEmpty(),
        firstPublishedYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0
    )
}

//fun Book.toBookEntity(): BookEntity {
//    return BookEntity(
//        id = id,
//        title = title,
//        description = description,
//        imageUrl = imageUrl,
//        languages = languages,
//        authors = authors,
//        firstPublishYear = firstPublishYear,
//        ratingsAverage = averageRating,
//        ratingsCount = ratingCount,
//        numPagesMedian = numPages,
//        numEditions = numEditions
//    )
//}

//fun BookEntity.toBook(): Book {
//    return Book(
//        id = id,
//        title = title,
//        description = description,
//        imageUrl = imageUrl,
//        languages = languages,
//        authors = authors,
//        firstPublishYear = firstPublishYear,
//        averageRating = ratingsAverage,
//        ratingCount = ratingsCount,
//        numPages = numPagesMedian,
//        numEditions = numEditions
//    )
//}