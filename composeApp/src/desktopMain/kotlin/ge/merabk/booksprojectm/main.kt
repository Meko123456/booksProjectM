package ge.merabk.booksprojectm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ge.merabk.booksprojectm.app.App
import ge.merabk.booksprojectm.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "booksProjectM",
        ) {
            App()
        }
    }
}