package expressionParser.function

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toStringList

class AddStringValueFunction : KPFunctionInterface {

    override fun execute(args: List<Any>): Double {
        val argsString = args.toStringList()
        println("argsString = $argsString")
        if (argsString.count() != 2) return 0.0
        return argsString[0].toDouble() + argsString[1].toDouble()
    }
}