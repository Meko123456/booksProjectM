package ge.merabk.booksprojectm.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ge.merabk.booksprojectm.book.domain.model.Book
import ge.merabk.booksprojectm.book.domain.usecase.SearchBooksUseCase
import ge.merabk.booksprojectm.core.domain.onError
import ge.merabk.booksprojectm.core.domain.onSuccess
import ge.merabk.booksprojectm.core.presentation.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {

    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }
        searchBooksUseCase(query)
            .onSuccess { searchResults ->
                _state.update {
                    it.copy(
                        error = null,
                        searchResults = searchResults,
                        isLoading = false
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        error = error.toUiText(),
                        isLoading = false
                    )

                }

            }
    }


    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                error = null,
                                searchResults = cachedBooks
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }


    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnBookClick -> {
                _state.update {
                    it.copy(searchResults = listOf(action.book))
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }
}

