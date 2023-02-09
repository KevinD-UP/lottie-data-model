package animationTransformer.AnimationTextTransformer

import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import org.codehaus.jettison.json.JSONObject

interface IAnimationTextTransformer {
    fun transformTexts(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules)
}