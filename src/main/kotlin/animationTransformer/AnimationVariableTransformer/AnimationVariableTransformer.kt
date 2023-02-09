package animationTransformer.AnimationVariableTransformer

import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import io.kannelle.models.VSWatermark
import org.codehaus.jettison.json.JSONObject

class AnimationVariableTransformer : IAnimationVariableTransformer {
    override fun transformVariables(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules, viewWidth: Int, viewHeight: Int, subtitleHeight: Int, watermark: VSWatermark?) {
        TODO("Not yet implemented")
    }
}
