package animationTransformer.AnimationVariableTransformer

import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import io.kannelle.models.VSWatermark
import org.codehaus.jettison.json.JSONObject

interface IAnimationVariableTransformer {
    fun transformVariables(
        animation: VSAnimation,
        composition: JSONObject,
        animationRules: AnimationRules,
        viewWidth: Int,
        viewHeight: Int,
        subtitleHeight: Int,
        watermark: VSWatermark?
    )
}