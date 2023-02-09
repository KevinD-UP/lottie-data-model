package animationTransformer.AnimationColorTransformer

import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import org.codehaus.jettison.json.JSONObject

interface IAnimationColorTransformer {
    fun transformColors(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules)
    fun transformAssetsColors(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules)
}