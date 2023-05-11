package expressionParser.functions

import expressionParser.KPFunctionInterface
import kotlin.math.min

class KPMinFunction: KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        if (args.count() != 2) {
            return 0.0
        }
        return min(args[0], args[1])
    }
}