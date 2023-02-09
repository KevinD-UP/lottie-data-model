package animationTransformer.AnimationEffectTransformer

import io.kannelle.models.VSAnimation
import org.codehaus.jettison.json.JSONObject

interface IAnimationEffectTransformer {
    fun transformLayerEffects(animation: VSAnimation, composition: JSONObject)
}