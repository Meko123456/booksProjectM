package ge.merabk.booksprojectm.di

import ge.merabk.booksprojectm.book.data.network.BookDataSource
import ge.merabk.booksprojectm.book.data.network.KtorBookDataSourceImpl
import ge.merabk.booksprojectm.book.data.repository.BooksRepositoryImpl
import ge.merabk.booksprojectm.book.data.usecase.GetBookDetailsUseCaseImpl
import ge.merabk.booksprojectm.book.data.usecase.SearchBooksUseCaseImpl
import ge.merabk.booksprojectm.book.domain.repository.BooksRepository
import ge.merabk.booksprojectm.book.domain.usecase.GetBookDetailsUseCase
import ge.merabk.booksprojectm.book.domain.usecase.SearchBooksUseCase
import ge.merabk.booksprojectm.book.presentation.booklist.BookListViewModel
import ge.merabk.booksprojectm.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    // Single for HttpClientFactory
    single { HttpClientFactory.create(get()) }

    // Single for KtorBookDataSource
    single<BookDataSource> { KtorBookDataSourceImpl(get()) }

    // Single for BooksRepository
    single<BooksRepository> { BooksRepositoryImpl(get()) }

    // Use Cases
    single<GetBookDetailsUseCase> { GetBookDetailsUseCaseImpl(get()) }
    single<SearchBooksUseCase> { SearchBooksUseCaseImpl(get()) }

    // ViewModel registration
    viewModel { BookListViewModel(get()) }

}