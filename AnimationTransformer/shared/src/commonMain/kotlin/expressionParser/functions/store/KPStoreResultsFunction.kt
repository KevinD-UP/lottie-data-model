package expressionParser.functions.store

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toStringList

class KPStoreResultsFunction: KPFunctionInterface {
    private var storeResultsMap = mutableMapOf<String, Double>()

    override fun execute(args: List<Any>): Double {
        val argsString = args.toStringList()
        val key = argsString.firstOrNull() ?: return 0.0
        if (args.count() != 1) return 0.0
        return storeResultsMap[key] ?: 0.0
    }

    fun addResult(key: String, result: Double) {
        println("add key[$key] = $result)")
        storeResultsMap[key] = result
    }
}