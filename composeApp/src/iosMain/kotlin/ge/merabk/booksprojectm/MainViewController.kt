package ge.merabk.booksprojectm

import androidx.compose.ui.window.ComposeUIViewController
import ge.merabk.booksprojectm.app.App
import ge.merabk.booksprojectm.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}