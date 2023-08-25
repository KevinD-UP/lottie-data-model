package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPShapeGroup
import lottieAnimation.layer.KPShapeLayer
import lottieAnimation.layer.KPShapeTransform
import lottieAnimation.layer.properties.KPMultiDimensionalListOrPrimitive
import lottieAnimation.layer.properties.KPMultiDimensionalPrimitive
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPOpacityTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class KPOpacityTransformerTest {
}