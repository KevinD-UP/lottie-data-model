package lottieAnimation.layer

interface VisualObject {
    /**
     * Name, as seen from editors and the like
     */
    val nm: String?

    /**
     * Match name, used in expressions
     */
    val mn: String?
}