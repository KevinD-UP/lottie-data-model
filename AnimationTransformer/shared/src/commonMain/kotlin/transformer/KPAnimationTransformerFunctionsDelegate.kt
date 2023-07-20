package transformer

import kotlin.js.JsName

interface KPAnimationTransformerFunctionsDelegate {
    @JsName("getAscent")
    fun getAscent(text: String, fontName: String, fontSize: Double): Double
    @JsName("getDescent")
    fun getDescent(text: String, fontName: String, fontSize: Double): Double
    @JsName("getLastLineBottom")
    fun getLastLineBottom(text: String, fontName: String, fontSize: Double): Double
    @JsName("getTextMeasureHeight")
    fun getTextMeasureHeight(
        text: String,
        fontName: String, fontSize: Double,
        layerSize: List<Double>?, layerLineHeight: Double, layerTracking: Double): Double
    @JsName("getTextLayerWidth")
    fun getTextLayerWidth(
        text: String,
        fontName: String, fontSize: Double): Double
}