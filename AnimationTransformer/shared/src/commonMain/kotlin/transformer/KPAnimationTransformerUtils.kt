package transformer

fun hexaStringToRGBAFloatList(argbString: String): List<Float>? {
    return hexaStringToRGBAFloatArray(argbString)?.toList()
}

fun hexaStringToRGBAFloatArray(argbString: String): FloatArray? {
    return try {
        val argb = argbString.trim().removePrefix("#")

        // Extract the individual RGB components
        val red: Int
        val green: Int
        val blue: Int
        val alpha: Int

        when (argb.length) {
            6 -> {
                // 6-digit ARGB string (add default alpha FF)
                red = argb.substring(0, 2).toInt(16)
                green = argb.substring(2, 4).toInt(16)
                blue = argb.substring(4, 6).toInt(16)
                alpha = 255 // Default alpha value FF (fully opaque)
            }
            8 -> {
                // 8-digit ARGB string
                alpha = argb.substring(0, 2).toInt(16)
                red = argb.substring(2, 4).toInt(16)
                green = argb.substring(4, 6).toInt(16)
                blue = argb.substring(6, 8).toInt(16)
            }
            else -> {
                throw IllegalArgumentException("Invalid ARGB string: $argbString")
            }
        }

        // Normalize the component values to the range of 0.0 to 1.0
        val floatAlpha = alpha.toFloat() / 255
        val floatRed = red.toFloat() / 255
        val floatGreen = green.toFloat() / 255
        val floatBlue = blue.toFloat() / 255

        // Create and return the float array
        floatArrayOf(floatRed, floatGreen, floatBlue)
    } catch (e: Exception) {
        null
    }

}