package ge.merabk.booksprojectm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform