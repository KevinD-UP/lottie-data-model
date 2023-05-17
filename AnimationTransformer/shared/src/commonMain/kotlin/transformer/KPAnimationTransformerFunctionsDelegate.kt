package transformer

interface KPAnimationTransformerFunctionsDelegate {
    fun getFirstLineTop(text: String, fontName: String, fontSize: Double): Double
    fun getDescent(text: String, fontName: String, fontSize: Double): Double
    fun getLastLineBottom(text: String, fontName: String, fontSize: Double): Double
    fun getTextMeasureHeight(
        text: String,
        fontName: String, fontSize: Double,
        layerSize: List<Double>?, layerLineHeight: Double, layerTracking: Double): Double

    fun getTextLayerWidth(
        text: String,
        fontName: String, fontSize: Double): Double
}