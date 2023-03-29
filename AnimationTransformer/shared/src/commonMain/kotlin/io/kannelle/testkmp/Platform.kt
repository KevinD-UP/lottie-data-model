package io.kannelle.testkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform