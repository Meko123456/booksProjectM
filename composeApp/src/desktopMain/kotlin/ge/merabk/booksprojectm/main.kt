package ge.merabk.booksprojectm

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "booksProjectM",
    ) {
        App(engine = remember { OkHttp.create() })
    }
}