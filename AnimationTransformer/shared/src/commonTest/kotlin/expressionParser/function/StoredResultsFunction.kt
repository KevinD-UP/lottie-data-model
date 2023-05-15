package expressionParser.function

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toStringList

class StoredResultsFunction : KPFunctionInterface {

    private val maps = mapOf(
        "key0" to 123.0,
        "key1" to 321.0,
        "key3" to 777.0
    )

    override fun execute(args: List<Any>): Double {
        val argsString = args.toStringList()
        println("argsString[0] = ${argsString[0]}")
        return maps[argsString[0]] ?: 0.0
    }
}