package io.kannelle.testkmp



class JsPlatform: Platform {
    override val name: String = "js"
}

actual fun getPlatform(): Platform = JsPlatform()