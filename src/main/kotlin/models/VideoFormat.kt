package io.kannelle.models

object VideoFormat {
    const val LANDSCAPE_16_9 = "16:9"
    const val SQUARE = "1:1"
    const val PORTRAIT_9_16 = "9:16"


    fun getVideoFormat(width: Float, height: Float): String {
        val ratio = width / height
        return when (ratio) {
            16 / 9f -> LANDSCAPE_16_9
            1f -> SQUARE
            9 / 16f -> PORTRAIT_9_16
            else -> LANDSCAPE_16_9
        }
    }

    fun getAspectRation(videoFormat: String?): Float {
        return when (videoFormat) {
            LANDSCAPE_16_9 -> 16 / 9f
            SQUARE -> 1f
            PORTRAIT_9_16 -> 9 / 16f
            else -> 16 / 9f
        }
    }

    fun getReferenceWidth(videoFormat: String?): Float {
        return when (videoFormat) {
            LANDSCAPE_16_9 -> 1920f
            SQUARE -> 1440f
            PORTRAIT_9_16 -> 1080f
            else -> 1920f
        }
    }

    fun getReferenceHeight(videoFormat: String?): Float {
        return when (videoFormat) {
            LANDSCAPE_16_9 -> 1080f
            SQUARE -> 1440f
            PORTRAIT_9_16 -> 1920f
            else -> 1080f
        }
    }

}