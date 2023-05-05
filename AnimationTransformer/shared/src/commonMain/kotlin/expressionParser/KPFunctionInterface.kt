package expressionParser

/**
 * KPFunctionInterface defines a contract for implementing mathematical functions.
 *
 * Implementations should take a list of Double arguments and return a Double result.
 * This interface can be used to represent various mathematical functions, such as
 * addition, subtraction, multiplication, or more complex operations.
 */
interface KPFunctionInterface {

    /**
     * Executes the mathematical function using the provided arguments.
     *
     * @param args A list of Double values representing the input arguments for the function.
     * @return A Double value representing the result of the function execution.
     * @throws IllegalArgumentException If the provided arguments are not valid for the function.
     */
    fun execute(args: List<Double>): Double
}