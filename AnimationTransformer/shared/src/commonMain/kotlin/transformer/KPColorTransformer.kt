package transformer

import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.*
import lottieAnimation.rules.properties.KPAnimationRules
import kotlin.js.JsName

class KPColorTransformer {

    private val json = Json {
        prettyPrint = true
    }

    fun transformColor(
        animation: KPLottieAnimation,
        animationRules: KPAnimationRules?,
        colors: Map<String, String>?
    ): KPLottieAnimation {

        println("PHETS transformColor")

        val modifiedAnimation = animation.copy()
        if (colors == null) return modifiedAnimation

        val layers: MutableList<KPLayer> = modifiedAnimation.layers.toMutableList()

        if(animationRules == null) {
            val paletteLayer = layers.find { it.nm == "palette" } as KPNullLayer?
            if (paletteLayer != null) {
                changePalette(paletteLayer, colors)
                return modifiedAnimation
            }
            return modifiedAnimation
        }

        animationRules.layerRules.forEach { layerRule ->

            if (layerRule.opacityKey != null) {
                val color = colors[layerRule.opacityKey]
                val colorDouble = color?.toDoubleOrNull()
                val targetLayer = layers.find { it.ind == layerRule.ind }
                if (colorDouble != null && targetLayer != null) {
                    setLayerStaticOpacity(targetLayer as KPVisualLayer, colorDouble * 100f)
                }
            }

            if (layerRule.colorKey != null) {
                val color = colors[layerRule.colorKey]
                val targetLayer = layers.find { it.ind == layerRule.ind }

                if (color != null && targetLayer != null) {
                    val colorArray = hexaStringToRGBAFloatArray(color) ?: return@forEach
                    print("PHETS fillcolor = ")
                    colorArray.forEach {
                        print("-$it-")
                    }
                    println("")


                    // Set layer fill effect color depending on its type
                    layerRule.fillColorKey?.let { fillColorKey ->
                        val fillColor = colors[fillColorKey] ?: return@let
                        val fillColorArray = hexaStringToRGBAFloatArray(fillColor) ?: return@let

                        setLayerFillEffectColor(
                            targetLayer,
                            colorArray,
                            fillColorArray
                        )

                        setLayerTextAnimationFillColor(
                            targetLayer,
                            colorArray,
                            fillColorArray
                        )
                    }

                    // Set layer color depending on its type
                    when (targetLayer) {
                        is KPTextLayer -> setTextColor(colorArray, targetLayer)
                        is KPSolidLayer -> setSolidColor(color, targetLayer)
                        is KPShapeLayer -> {
                            setShapeColor(colorArray, targetLayer)
                        }
                        else -> {

                            /* Unsupported layers */
                        }
                    }

                }
            }

            layerRule.colorKeys?.forEachIndexed { index, colorKey ->
                val color = colors[colorKey]
                val targetLayer = animation.layers.find { it.ind == layerRule.ind }

                println("PHETS colorsKey $colorKey")
                if (color != null && targetLayer != null) {
                    val colorArray = hexaStringToRGBAFloatArray(color) ?: return@forEachIndexed
                    // Set layer color depending on its type
                    when (targetLayer) {
                        is KPTextLayer -> {
                            // TODO: To implement
                        }
                        is KPSolidLayer -> {
                            // TODO: To implement
                        }
                        is KPShapeLayer -> setShapeColor(colorArray, targetLayer, index)
                        else -> {
                            /* Unhandled Layers Fall Through */
                        }
                    }
                }
            }

            if (layerRule.gradientColorKey != null) {
                layerRule.gradientColorKey.forEachIndexed { index, gradientColorKey ->
                    val color = colors[gradientColorKey]
                    val targetLayer = animation.layers.find { it.ind == layerRule.ind }
                    if (color != null && targetLayer != null) {
                        setContainerGradientColor(color, index, targetLayer)
                    }
                }
            }

            if (layerRule.shadowKey != null) {
                val shadowColor = colors[layerRule.shadowKey]
                shadowColor?.let {
                    val colorArray = hexaStringToRGBAFloatArray(shadowColor) ?: return@let
                    val targetLayer = animation.layers.find { it.ind == layerRule.ind } ?: return@let
                    setShadowLayer(targetLayer as KPVisualLayer, colorArray)
                }
            }

            if (layerRule.shadowOpacityKey != null) {
                val shadowOpacity = colors[layerRule.shadowOpacityKey]
                shadowOpacity?.let {
                    val colorDouble = shadowOpacity.toDoubleOrNull() ?: return@let
                    val targetLayer = animation.layers.find { it.ind == layerRule.ind } ?: return@let
                    setTextShadowOpacity(targetLayer as KPVisualLayer, colorDouble * 100f)
                }
            }
        }

        modifiedAnimation.layers = layers

        return modifiedAnimation
    }

    private fun setSolidColor(color: String, layer: KPSolidLayer) {
        layer.sc = color
    }

    private fun setTextColor(colorArray: FloatArray, textLayer: KPTextLayer) {
        textLayer.t.d.k.first().s.fc = listOf(
            JsonPrimitive(colorArray[0]), // R
            JsonPrimitive(colorArray[1]), // G
            JsonPrimitive(colorArray[2]) // B
        )
    }

    private fun setTextShadowOpacity(layer: KPVisualLayer, colorDouble: Double) {
        layer.ef?.firstOrNull { it.ty == 25 }?.ef?.firstOrNull { it.ty == 0 }?.v?.k = KPMultiDimensionalPrimitive(JsonPrimitive(colorDouble))
    }

    private fun setShadowLayer(layer: KPVisualLayer, colorArray: FloatArray) {
        val newShadowColor = listOf(
            KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[0])),
            KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[1])),
            KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[2])),
            KPMultiDimensionalNodePrimitive(JsonPrimitive(1)),
        )
        layer.ef?.firstOrNull { it.ty == 25 }?.ef?.firstOrNull { it.ty == 2 }?.v?.k = KPMultiDimensionalList(newShadowColor)
    }

    private fun setContainerGradientColor(color: String, index: Int, layer: KPLayer) {
        val colorArray = hexaStringToRGBAFloatArray(color) ?: return

        if (layer is KPShapeLayer) {
            val shapeGroup = layer.shapes?.get(0) as KPShapeGroup
            val gradientFill = shapeGroup.it.firstOrNull { it is KPShapeGFill } as KPShapeGFill?
            val gradientStroke = shapeGroup.it.firstOrNull { it is KPShapeGStroke } as KPShapeGStroke?

            gradientFill?.let {
                val currentGradientColor = it.g.k.k
                if (currentGradientColor is KPMultiDimensionalList) {
                    println("PHETS currentGradientColor $index = $currentGradientColor")
                    val newGradientColor = currentGradientColor.values.toMutableList()
                    newGradientColor[index * 4 + 1] = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[0]))
                    newGradientColor[index * 4 + 2] = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[1]))
                    newGradientColor[index * 4 + 3] = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[2]))
                    //newGradientColor.add(index = index * 4 + 1, element = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[0]))) //R
                    //newGradientColor.add(index = index * 4 + 2, element = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[1]))) //G
                    //newGradientColor.add(index = index * 4 + 3, element = KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[2]))) //B
                    println("PHETS newGradientColor $index = $newGradientColor")
                    it.g.k.k = KPMultiDimensionalList(newGradientColor)
                }
            }

            if (gradientFill == null) {
                gradientStroke?.let {
                    // We haven't encountered any example with GStroke Gradient. Using Json Directly
                    val currentGradientColor = it.g?.jsonObject?.mutate {
                        val k = get("k")?.jsonObject
                        val subK = k?.get("k")?.jsonArray?.toMutableList()
                        subK?.add(index = index * 4 + 1, element = JsonPrimitive(colorArray[0])) //R
                        subK?.add(index = index * 4 + 2, element = JsonPrimitive(colorArray[1])) //G
                        subK?.add(index = index * 4 + 3, element = JsonPrimitive(colorArray[2])) //B

                        val newK = k?.mutate {
                            put("k", JsonArray(subK ?: emptyList()))
                        }
                        newK?.toJsonElement()?.let { it1 -> put("k", it1) }
                    }
                    it.g = currentGradientColor
                }
            }
        }
    }

    private fun setShapeColor(colorArray: FloatArray, layer: KPShapeLayer, keyframeIndex: Int = 0) {
        // Stroke
        val hasStrokeColor = getShapeStrokeColor(layer) != null
        if (hasStrokeColor) {
            setShapeStrokeColor(colorArray, layer)
        }

        // Fill
        val hasFillColor = getShapeFillColor(layer, keyframeIndex) != null
        if (hasFillColor) {
            setShapeFillColor(colorArray, layer, keyframeIndex)
        }
    }

    private fun setShapeFillColor(colorArray: FloatArray, layer: KPShapeLayer, keyframeIndex: Int = 0) {
        val shapes = layer.shapes ?: return
        shapes.forEach { shape ->
            if (shape is KPShapeFill) {
                val newFillColor = listOf(
                    JsonPrimitive(colorArray[0]), // R
                    JsonPrimitive(colorArray[1]), // G
                    JsonPrimitive(colorArray[2]), // B
                    JsonPrimitive(1) // A (not used for opacity, use "o" instead)
                )

                val animated = shape.c.a?.int == 1
                if (!animated) {
                    (shape.c.k as KPMultiDimensionalList).values.take(4).mapIndexed { index, keyframe ->
                        keyframe as KPMultiDimensionalNodePrimitive
                        keyframe.value = newFillColor[index]
                    }
                } else {
                    val keyframes = shape.c.k as KPMultiDimensionalList
                    val animatedKeyframe = keyframes.values[keyframeIndex] as KPMultiDimensionNodeObject
                    animatedKeyframe.s = JsonArray(newFillColor)
                }
            }

            if (shape is KPShapeGroup) {
                seekSetShapeFillColor(colorArray, shape, keyframeIndex)
            }
        }
    }

    private fun seekSetShapeFillColor(colorArray: FloatArray, shapeGroup: KPShapeGroup, keyframeIndex: Int) {
        shapeGroup.it.forEach { shape ->
            if (shape is KPShapeFill) {
                val newFillColor = listOf(
                    JsonPrimitive(colorArray[0]), // R
                    JsonPrimitive(colorArray[1]), // G
                    JsonPrimitive(colorArray[2]), // B
                    JsonPrimitive(1) // A (not used for opacity, use "o" instead)
                )

                val animated = shape.c.a?.int == 1
                if (!animated) {
                    (shape.c.k as KPMultiDimensionalList).values.take(4).mapIndexed { index, keyframe ->
                        keyframe as KPMultiDimensionalNodePrimitive
                        keyframe.value = newFillColor[index]
                    }
                } else {
                    val keyframes = shape.c.k as KPMultiDimensionalList
                    val animatedKeyframe = keyframes.values[keyframeIndex] as KPMultiDimensionNodeObject
                    animatedKeyframe.s = JsonArray(newFillColor)
                }
            }
            if (shape is KPShapeGroup) {
                seekSetShapeFillColor(colorArray, shape, keyframeIndex)
            }
        }
    }

    private fun getShapeFillColor(layer: KPShapeLayer, keyframeIndex: Int = 0): FloatArray? {
        val shapeZero = layer.shapes?.get(0)
        return if (shapeZero is KPShapeGroup) {
            val fillShape = shapeZero.it.firstOrNull { it is KPShapeFill } as KPShapeFill? ?: return null
            val keyFrames = fillShape.c.k
            val animated = fillShape.c.a?.int == 1
            return if (!animated) {
                if (keyFrames is KPMultiDimensionalList) {
                    keyFrames.values.take(3).map { keyframe ->
                        keyframe as KPMultiDimensionalNodePrimitive
                        keyframe.value.float
                    }.toFloatArray()
                } else {
                    null
                }
            } else {
                if (keyFrames is KPMultiDimensionalList) {
                    val animatedKeyframe = keyFrames.values[keyframeIndex]
                    if (animatedKeyframe is KPMultiDimensionNodeObject) {
                        animatedKeyframe.s?.toRGBFloatArray
                    } else {
                        null
                    }
                } else {
                    null
                }
            }
        } else {
            null
        }
    }

    private fun setShapeStrokeColor(colorArray: FloatArray, layer: KPShapeLayer) {
        layer.shapes ?: return
        layer.shapes?.let { shapes ->
            shapes.forEach { shape ->
                if (shape is KPShapeStroke) {
                    when (val kType = shape.c?.k) {
                        is KPMultiDimensionalList -> {
                            when (kType.values.firstOrNull()) {
                                is KPMultiDimensionalNodePrimitive -> {
                                    val newStrokeColor = listOf(
                                        KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[0])), // R
                                        KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[1])), // G
                                        KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[2])), // B
                                        KPMultiDimensionalNodePrimitive(JsonPrimitive(1)) // A (not used for opacity, use "o" instead)
                                    )
                                    shape.c?.k = KPMultiDimensionalList(values = newStrokeColor)
                                }
                                else -> {

                                }
                            }
                        }
                        else -> { }
                    }
                }

                if (shape is KPShapeGroup) {
                    seekSetShapeStrokeColor(colorArray, shape)
                }
            }
        }
    }

    private fun seekSetShapeStrokeColor(colorArray: FloatArray, shapeGroup: KPShapeGroup) {
        shapeGroup.it.forEach { shape ->
            if (shape is KPShapeStroke) {
                when (val kType = shape.c?.k) {
                    is KPMultiDimensionalList -> {
                        when (kType.values.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                val newStrokeColor = listOf(
                                    KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[0])), // R
                                    KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[1])), // G
                                    KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray[2])), // B
                                    KPMultiDimensionalNodePrimitive(JsonPrimitive(1)) // A (not used for opacity, use "o" instead)
                                )
                                shape.c?.k = KPMultiDimensionalList(values = newStrokeColor)
                            }
                            else -> {

                            }
                        }
                    }
                    else -> { }
                }
            }
            if (shape is KPShapeGroup) {
                seekSetShapeStrokeColor(colorArray, shape)
            }
        }
    }

    private fun getShapeStrokeColor(layer: KPShapeLayer): FloatArray? {
        return when (val shape = layer.shapes?.getOrNull(0)) {
            is KPShapeGroup -> {
                val strokeShape = shape.it.firstOrNull { it is KPShapeStroke } as? KPShapeStroke
                return when (val colorList = strokeShape?.c?.k) {
                    is KPMultiDimensionalList -> {
                        when (colorList.values.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                val r = colorList.values[0] as KPMultiDimensionalNodePrimitive
                                val g = colorList.values[1] as KPMultiDimensionalNodePrimitive
                                val b = colorList.values[2] as KPMultiDimensionalNodePrimitive
                                floatArrayOf(
                                    r.value.float,
                                    g.value.float,
                                    b.value.float
                                )
                            }
                            else -> {
                                null
                            }
                        }
                    }
                    else -> {
                        null
                    }
                }
            }
            else -> null
        }
    }

    private fun setLayerTextAnimationFillColor(layer: KPLayer, colorArray: FloatArray, fillColorArray: FloatArray) {
        val currentLayerColor = when (layer) {
            is KPTextLayer -> getTextColor(layer)
            is KPShapeLayer -> null // TODO: To Implement
            is KPSolidLayer -> null // TODO: To Implement
            else -> null
        } ?: return

        if (layer is KPTextLayer) {
            layer.t.a.firstOrNull { it.a.fc != null }?.a?.fc?.k?.let { k ->
                if (k is KPMultiDimensionalList) {
                    k.values.forEach { keyframe ->
                        handleKeyframeColor(keyframe, currentLayerColor, colorArray, fillColorArray)
                    }
                }
            }
        }
    }

    private fun setLayerFillEffectColor(layer: KPLayer, colorArray: FloatArray, fillColorArray: FloatArray) {

        val currentLayerColor = when (layer) {
            is KPTextLayer -> getTextColor(layer)
            is KPShapeLayer -> null // TODO: To Implement
            is KPSolidLayer -> null // TODO: To Implement
            else -> null
        } ?: return

        layer as KPVisualLayer
        layer.ef?.firstOrNull { it.ty == 21 }?.ef?.firstOrNull { it.ty == 2 }?.v?.k?.let { k ->
            if (k is KPMultiDimensionalList) {
                k.values.forEach { keyframe ->
                    handleKeyframeColor(keyframe, currentLayerColor, colorArray, fillColorArray)
                }
            }
        }
    }

    private fun handleKeyframeColor(keyframe: KPMultiDimensionalNodeObjectOrPrimitive, currentLayerColor: FloatArray, colorArray: FloatArray, fillColorArray: FloatArray) {
        if (keyframe is KPMultiDimensionNodeObject) {
            val startColorJsonArray = keyframe.s
            val endColorJsonArray = keyframe.e

            // Start
            if (startColorJsonArray != null) {
                if (startColorJsonArray.toRGBFloatArray.contentEquals(currentLayerColor)) {
                    // Set keyframe start color with colorArray
                    keyframe.s = colorArray.toJsonArray()
                } else {
                    // Set keyframe start color with fillColorArray
                    keyframe.s = fillColorArray.toJsonArray()
                }
            }

            // End
            if (endColorJsonArray != null) {
                if (endColorJsonArray.toRGBFloatArray.contentEquals(currentLayerColor)) {
                    // Set keyframe end color with colorArray
                    keyframe.e = colorArray.toJsonArray()
                } else {
                    // Set keyframe end color with fillColorArray
                    keyframe.e = fillColorArray.toJsonArray()
                }
            }
        }
    }

    private fun getTextColor(layer: KPTextLayer): FloatArray {
        val fc = layer.t.d.k.first().s.fc
        return floatArrayOf(
            fc[0].jsonPrimitive.float,
            fc[1].jsonPrimitive.float,
            fc[2].jsonPrimitive.float
        )
    }

    private fun setLayerStaticOpacity(layer: KPVisualLayer, opacity: Double) {
        if (layer.ks.o is KPMultiDimensionalSimple) {
            val animated = (layer.ks.o as KPMultiDimensionalSimple).a?.int == 1
            if (!animated) {
                (layer.ks.o as KPMultiDimensionalSimple).k = KPMultiDimensionalPrimitive(JsonPrimitive(opacity))
            } else {
                val newK = (layer.ks.o as KPMultiDimensionalSimple).k as KPMultiDimensionalList
                newK.values.forEach { opacityKeyframe ->
                    setOpacityColorForKeyframe(opacityKeyframe, opacity)
                }
                (layer.ks.o as KPMultiDimensionalSimple).k = newK
            }
        }
    }

    private fun setOpacityColorForKeyframe(opacityKeyframe: KPMultiDimensionalNodeObjectOrPrimitive, opacity: Double) {
        if (opacityKeyframe is KPMultiDimensionNodeObject) {
            val opacityArray = buildJsonArray {
                add(opacity)
            }
            println("PHETS opacityArray = $opacityArray")
            println("PHETS opacityKeyframe OLD = $opacityKeyframe")
            val startOpacity = opacityKeyframe.s
            startOpacity?.let {
                val currentOpacity = it[0].jsonPrimitive.float
                if (currentOpacity > 0f) {
                    opacityKeyframe.s = opacityArray
                }
            }

            val endOpacity = opacityKeyframe.e
            endOpacity?.let {
                val currentOpacity = it[0].jsonPrimitive.float
                if (currentOpacity > 0f) {
                    opacityKeyframe.e = opacityArray
                }
            }
            println("PHETS opacityKeyframe NEW = $opacityKeyframe")
        }
    }

    // ---------------------- UTILS ----------------------

    private fun JsonObject.mutate(mutate: MutableMap<String, JsonElement>.() -> Unit): JsonObject {
        return JsonObject(this.toMutableMap().apply {
            // edit like ordinary mutable map
            mutate(this)
        })
    }

    private fun Map<*, *>.toJsonElement(): JsonElement {
        val map: MutableMap<String, JsonElement> = mutableMapOf()
        this.forEach {
            val key = it.key as? String ?: return@forEach
            val value = it.value ?: return@forEach
            when (value) {
                // convert containers into corresponding Json containers
                is Map<*, *> -> map[key] = (value).toJsonElement()
                is List<*> -> map[key] = value.toJsonElement()
                // convert the value to a JsonPrimitive
                else -> map[key] = JsonPrimitive(value.toString())
            }
        }
        return JsonObject(map)
    }

    private fun List<*>.toJsonElement(): JsonElement {
        val list: MutableList<JsonElement> = mutableListOf()

        this.forEach {
            val value = it ?: return@forEach
            when (value) {
                is Map<*, *> -> list.add((value).toJsonElement())
                is List<*> -> list.add(value.toJsonElement())
                else -> list.add(JsonPrimitive(value.toString()))
            }
        }

        return JsonArray(list)
    }

    private fun changePalette(paletteLayer: KPNullLayer, colors: Map<String, String>){
        var i = 1;
        colors.forEach { (key, value) ->
            val colorEffect = paletteLayer.ef?.find { it.nm.toString().removeSurrounding("\"") == key }
            val colorArray = hexaStringToRGBAFloatArray(value)
            colorEffect?.ef?.get(0)?.v?.k = KPMultiDimensionalList(listOf(
                KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray?.get(0))), // R
                KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray?.get(1))), // G
                KPMultiDimensionalNodePrimitive(JsonPrimitive(colorArray?.get(2))), // B
                KPMultiDimensionalNodePrimitive(JsonPrimitive(1)) // A (not used for opacity, use "o" instead)
            ))

            val colorOpacityEffect = paletteLayer.ef?.find { it.nm.toString().removeSurrounding("\"") == "color_opacity$i" }
            colorOpacityEffect?.ef?.get(0)?.v?.k =  KPMultiDimensionalPrimitive(JsonPrimitive(value.opacityFromHex()))
            i++
        }
    }

    private fun FloatArray.toJsonArray(): JsonArray {
        return buildJsonArray {
            this@toJsonArray.forEach {
                add(it)
            }
        }
    }

    private val JsonArray.toRGBFloatArray: FloatArray
        get() {
            val list = mutableListOf<Float>()
            for (i in 0 until count()) {
                if (i < 3) {
                    list.add(i, this[i].jsonPrimitive.float)
                }
            }
            return list.toFloatArray()

        }

    private fun String.opacityFromHex(): Int {
        if (!this.startsWith('#')) {
            return 100
        }

        return when (this.length) {
            9 -> { // 8-digit hex + '#'
                // Extract the alpha component (last 2 characters)
                val alphaHex = this.takeLast(2)

                return alphaHex.toInt()
            }
            else -> 100
        }
    }
}