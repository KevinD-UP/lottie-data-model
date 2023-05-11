package expressionParser

/**
 * KPExpressionParser defines a contract for implementing expression parsers.
 *
 * Implementations should take a string representing a mathematical expression and
 * return the result of the expression as a Double. This interface can be used to create
 * various parsers for different expression formats, such as infix, prefix, or postfix notation.
 */
interface KPExpressionParser {
    /**
     * Parse and evaluate a given string expression.
     *
     * The expression can include numbers, arithmetic operators (+, -, *, /),
     * parentheses for grouping, and custom functions with parameters.
     *
     * Custom functions should be defined in the implementing class and
     * follow the naming convention of alphanumeric characters and underscores.
     * Function names are case-sensitive.
     *
     * Function parameters should be separated by commas and enclosed within
     * parentheses. Nested function calls are also supported.
     *
     * Examples of valid expressions:
     * - "2 + 3 * 4"
     * - "(3 + 5) * 2"
     * - "myFunction(2, 4) * 3"
     * - "anotherFunction(1, myFunction(2, 3))"
     *
     * @param expression The string expression to parse and evaluate.
     * @return The result of the evaluated expression as a Double.
     * @throws IllegalArgumentException If the expression contains an invalid token or function name.
     * @throws NoSuchElementException If the token list is empty or has a mismatched parenthesis.
     */
    fun parseAndEvaluate(expression: String): Double
}
