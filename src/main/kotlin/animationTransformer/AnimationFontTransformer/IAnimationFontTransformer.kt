package animationTransformer.AnimationFontTransformer

import io.kannelle.models.AnimationRules
import io.kannelle.models.VSAnimation
import org.codehaus.jettison.json.JSONObject

interface IAnimationFontTransformer {
    fun transformFonts(animation: VSAnimation, composition: JSONObject, animationRules: AnimationRules)
}