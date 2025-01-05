package ge.merabk.booksprojectm.book.data.network

import ge.merabk.booksprojectm.book.data.dto.BookWorkDto
import ge.merabk.booksprojectm.book.data.dto.SearchResponseDto
import ge.merabk.booksprojectm.core.data.safeCall
import ge.merabk.booksprojectm.core.domain.DataError
import ge.merabk.booksprojectm.core.domain.BooksResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class KtorBookDataSourceImpl(
    private val httpClient: HttpClient
) : BookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): BooksResult<SearchResponseDto, DataError.Remote> = safeCall<SearchResponseDto> {
        httpClient.get(
            urlString = "$BASE_URL/search.json"
        ) {
            parameter("q", query)
            parameter("limit", resultLimit)
            parameter("language", "eng")
            parameter(
                "fields",
                "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average," +
                        "ratings_count,first_publish_year,language,number_of_pages_median," +
                        "edition_count"
            )
        }

    }

    override suspend fun getBookDetails(bookWorkId: String): BooksResult<BookWorkDto, DataError.Remote> =
        safeCall<BookWorkDto> {
            httpClient.get(
                urlString = "$BASE_URL/works/$bookWorkId.json"
            )
        }

    companion object {
        private const val BASE_URL = "https://openlibrary.org"
    }
}