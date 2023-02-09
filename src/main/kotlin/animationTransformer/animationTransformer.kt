package animationTransformer// TODO : delete
import org.codehaus.jettison.json.JSONObject

import animationTransformer.AnimationColorTransformer.IAnimationColorTransformer
import animationTransformer.AnimationEffectTransformer.IAnimationEffectTransformer
import animationTransformer.AnimationFontTransformer.IAnimationFontTransformer
import animationTransformer.AnimationTextTransformer.IAnimationTextTransformer
import animationTransformer.AnimationVariableTransformer.IAnimationVariableTransformer
import io.kannelle.models.*

class AnimationTransformer constructor(
    private val fontTransformer: IAnimationFontTransformer,
    private val textTransformer: IAnimationTextTransformer,
    private val colorTransformer: IAnimationColorTransformer,
    // TODO : put me back
    //private val sizeTransformer: AnimationSizeTransformer,
    private val variableTransformer: IAnimationVariableTransformer,
    private val effectTransformer: IAnimationEffectTransformer
) {
    fun transform(
        animation: VSAnimation,
        duration: Float,
        composition: JSONObject,
        viewWidth: Int,
        viewHeight: Int,
        subtitleHeight: Int,
        watermark: VSWatermark? = null
    ) {
        val layerRule = LayerRule(
            ind = 1,
            textInd = listOf(1),
            lines = 1,
            maxLines = 1,
            minWidth = 1,
            maxWidth = 1,
            maxHeight = 1,
            fontKey = "font",
            minFontSize = 1,
            colorKey = "color",
            colorKeys = listOf("colorKey2") ,
            fillColorKey = "fillColorKey",
            opacityKey = "opacityKey",
            gradientColorKey = listOf("gradientKey"),
            shadowKey = "shadowKey",
            shadowOpacityKey = "shadowOpacityKey",
            separator = "sep"
        )

        transformV2(
             animation,
            duration,
            composition,
            subtitleHeight,
            // TODO : pass true animation rules
            AnimationRules(listOf(layerRule), null, null),
            viewWidth,
            viewHeight,
            watermark
        )
    }

    private fun transformV2(
        animation: VSAnimation,
        duration: Float,
        composition: JSONObject,
        subtitleHeight: Int,
        animationRules: AnimationRules,
        viewWidth: Int,
        viewHeight: Int,
        watermark: VSWatermark?
    ) {
        fontTransformer.transformFonts(
            animation = animation,
            composition = composition,
            animationRules = animationRules
        )
        textTransformer.transformTexts(
            animation = animation,
            composition = composition,
            animationRules = animationRules
        )
        colorTransformer.transformColors(
            animation = animation,
            composition = composition,
            animationRules = animationRules
        )
        colorTransformer.transformAssetsColors(
            animation = animation,
            composition = composition,
            animationRules = animationRules
        )
        variableTransformer.transformVariables(
            animation = animation,
            composition = composition,
            animationRules = animationRules,
            viewWidth = viewWidth,
            viewHeight = viewHeight,
            subtitleHeight = subtitleHeight,
            watermark = watermark
        )
        effectTransformer.transformLayerEffects(
            animation = animation,
            composition = composition
        )
    }
}