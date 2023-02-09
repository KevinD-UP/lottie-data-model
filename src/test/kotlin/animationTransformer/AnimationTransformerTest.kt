package animationTransformer

import animationTransformer.AnimationColorTransformer.IAnimationColorTransformer
import animationTransformer.AnimationEffectTransformer.IAnimationEffectTransformer
import animationTransformer.AnimationFontTransformer.IAnimationFontTransformer
import animationTransformer.AnimationTextTransformer.IAnimationTextTransformer
import animationTransformer.AnimationVariableTransformer.IAnimationVariableTransformer
import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import io.kannelle.models.VSAnimationPosition
import io.kannelle.models.VSWatermark
import org.codehaus.jettison.json.JSONObject
import kotlin.test.Test
import kotlin.test.assertTrue

class AnimationTransformerTest {
    @Test
    fun callsUnderlyingTransformers() {
        val fontTransformerSpy = FontTransformerSpy()
        val textTransformerSpy = TextTransformerSpy()
        val colorTransformerSpy = ColorTransformerSpy()
        val variableTransformerSpy = VariableTransformerSpy()
        val effectTransformerSpy = EffectTransformerSpy()

        val animationTransformer = AnimationTransformer(
            fontTransformerSpy,
            textTransformerSpy,
            colorTransformerSpy,
            variableTransformerSpy,
            effectTransformerSpy
        )

        val animation = VSAnimation(
            "testAnimation",
            false,
            VSAnimationPosition(
                "testPosition",
                12.5f,
                14.8f
            ),
            1.5f,
            7.5f,
            arrayOf<String>("testText1", "testText2"),
            "testTheme",
            mapOf<String, String>("testColor1" to "#FFFFFF"),
            mapOf<String, String>("testFont1" to "Lato")
        )

        animationTransformer.transform(animation, 8.5f, JSONObject(), 1000, 1000, 0)

        assertTrue(fontTransformerSpy.transformFontsCalled)
        assertTrue(textTransformerSpy.TransformTextsCalled)
        assertTrue(colorTransformerSpy.transformColorsCalled)
        assertTrue(colorTransformerSpy.transformAssetsColorsCalled)
        assertTrue(variableTransformerSpy.transforVariablesCalled)
        assertTrue(effectTransformerSpy.transformLayerEffectsCalled)
    }
}

class FontTransformerSpy: IAnimationFontTransformer {
    var transformFontsCalled = false

    override fun transformFonts(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules) {
        transformFontsCalled = true
    }
}

class TextTransformerSpy: IAnimationTextTransformer {
    var TransformTextsCalled = false

    override fun transformTexts(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules) {
        TransformTextsCalled = true
    }
}

class ColorTransformerSpy: IAnimationColorTransformer {
    var transformColorsCalled = false
    var transformAssetsColorsCalled = false

    override fun transformColors(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules) {
        transformColorsCalled = true
    }

    override fun transformAssetsColors(
        animation: VSAnimation,
        composition: JSONObject,
        animationRules: AnimationRules
    ) {
        transformAssetsColorsCalled = true
    }
}

class VariableTransformerSpy: IAnimationVariableTransformer {
    var transforVariablesCalled = false

    override fun transformVariables(
        animation: VSAnimation,
        composition: JSONObject,
        animationRules: AnimationRules,
        viewWidth: Int,
        viewHeight: Int,
        subtitleHeight: Int,
        watermark: VSWatermark?
    ) {
        transforVariablesCalled = true
    }
}

class EffectTransformerSpy: IAnimationEffectTransformer {
    var transformLayerEffectsCalled = false

    override fun transformLayerEffects(animation: VSAnimation, composition: JSONObject) {
        transformLayerEffectsCalled = true
    }
}