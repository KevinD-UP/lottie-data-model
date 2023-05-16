package expressionParser

class KPDefaultExpressionParser(private val functions: Map<String, KPFunctionInterface>): KPExpressionParser {
    private lateinit var tokens: List<String>
    private var position: Int = 0

    override fun parseAndEvaluate(expression: String): Double {
        tokens = tokenize(expression)
        position = 0
        return parseExpression()
    }

    private fun tokenize(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        val regex = Regex("(\\d+|[(),+\\-*/]|\"[^\"]*\"|[a-zA-Z_][a-zA-Z0-9_]*)")

        regex.findAll(expression).forEach { matchResult ->
            tokens.add(matchResult.value)
        }

        return tokens
    }

    private fun parseExpression(): Double {
        var result = parseTerm()
        while (position < tokens.size) {
            val token = tokens[position]
            if (token == "+" || token == "-") {
                position++
                val termResult = parseTerm()
                result = if (token == "+") result + termResult else result - termResult
            } else {
                break
            }
        }
        return result
    }

    private fun parseTerm(): Double {
        var result = parseFactor()
        while (position < tokens.size) {
            val token = tokens[position]
            if (token == "*" || token == "/") {
                position++
                val factorResult = parseFactor()
                result = if (token == "*") result * factorResult else result / factorResult
            } else {
                break
            }
        }
        return result
    }

    private fun parseFactor(): Double {
        var sign = 1.0
        if (tokens[position] == "-") {
            sign = -1.0
            position++
        }

        val token = tokens[position++]
        return when {
            token == "(" -> {
                val result = parseExpression()
                if (tokens[position] == ")") {
                    position++ // Consume the closing parenthesis
                }
                result * sign
            }
            token.matches(Regex("\\d+")) -> token.toDouble() * sign
            token.matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*")) -> parseFunctionCall() * sign
            else -> throw IllegalArgumentException("Unexpected token: $token")
        }
    }


    private fun parseFunctionCall(): Double {
        val functionName = tokens[position - 1]
        position++ // Consume the opening parenthesis

        val function = functions[functionName]
            ?: throw IllegalArgumentException("Unexpected function: $functionName")

        val args = mutableListOf<Any>()

        if (tokens[position] != ")") {
            args.add(parseArgument())

            while (tokens[position] == ",") {
                position++ // Consume the comma separator
                args.add(parseArgument())
            }
        }

        if (tokens[position] == ")") {
            position++ // Consume the closing parenthesis
        }

        return function.execute(args)
    }

    private fun parseArgument(): Any {
        return when {
            tokens[position].matches(Regex("\\d+")) -> {
                val number = tokens[position].toDouble()
                position++
                number
            }
            tokens[position].matches(Regex("\"[^\"]*\"")) -> {
                val string = tokens[position].removeSurrounding("\"")
                position++
                string
            }
            tokens[position].matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*")) -> {
                if (functions.containsKey(tokens[position])) {
                    parseExpression()
                } else {
                    val string = tokens[position]
                    position++
                    string
                }
            }
            else -> parseExpression()
        }
    }
}
