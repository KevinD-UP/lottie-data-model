package expressionParser.extensions

fun List<Any>.toDoubleList(): List<Double> {
    return this.mapNotNull { item ->
        when (item) {
            is Double -> item
            else -> null
        }
    }
}

fun List<Any>.toStringList(): List<String> {
    return this.mapNotNull { item ->
        when (item) {
            is String -> item
            else -> null
        }
    }
}