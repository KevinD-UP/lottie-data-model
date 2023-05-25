package io.kannelle.testkmp.android.presentation

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import io.kannelle.testkmp.android.MyApplicationTheme
import transformer.KPAnimationTransformer

class FontTransformer(
    private val context: Context
) {

    enum class FontTypeAnim(val type: String) {
        AbhayaLibre("AbhayaLibre"),
        Arial("Arial"),
        BebasNeue("BebasNeue"),
        Roboto("Roboto"),
    }

    private val jsonString: MutableState<String> = mutableStateOf(context.assets.readAssetsFile("animations/BALI-PLANE.json"))
    private val dropDownMenuExpanded: MutableState<Boolean> = mutableStateOf(false)
    private val selectedFontName: MutableState<String> = mutableStateOf("Default")

    @Composable
    fun FontTransformerView() {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.JsonString(jsonString.value))
        Log.i("Orpheus","Composition: ${composition?.fonts}")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Selected Font: ${selectedFontName.value}",
                color = Color.Blue
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.height(300.dp),
                fontMap = mapOf(
                    "AbhayaLibre-ExtraBold.ttf" to Typeface.createFromAsset(context.assets, "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf"),

                    "Arial" to Typeface.createFromAsset(context.assets, "fonts/Arial/Arial.ttf"),

                    "BebasNeue-Bold" to Typeface.createFromAsset(context.assets, "fonts/BebasNeue/BebasNeue-Bold.otf"),
                    "BebasNeue-Regular" to Typeface.createFromAsset(context.assets, "fonts/BebasNeue/BebasNeue-Regular.otf"),

                    "Helvetica" to Typeface.createFromAsset(context.assets, "fonts/Helvetica/Helvetica.ttc"),
                    "Helvetica-Bold" to Typeface.createFromAsset(context.assets, "fonts/Helvetica/Helvetica-Bold.ttf"),

                    "HelveticaNeue-Bold" to Typeface.createFromAsset(context.assets, "fonts/HelveticaNeue/HelveticaNeue-Bold.ttf"),
                    "HelveticaNeue-Light" to Typeface.createFromAsset(context.assets, "fonts/HelveticaNeue/HelveticaNeue-Light.ttf"),

                    "Lato-Black" to Typeface.createFromAsset(context.assets, "fonts/Lato/Lato-Black.ttf"),
                    "Lato-BlackItalic" to Typeface.createFromAsset(context.assets, "fonts/Lato/Lato-BlackItalic.ttf"),
                    "Lato-Bold" to Typeface.createFromAsset(context.assets, "fonts/Lato/Lato-Bold.ttf"),
                    "Lato-Regular" to Typeface.createFromAsset(context.assets, "fonts/Lato/Lato-Regular.ttf"),

                    "Roboto-Black" to Typeface.createFromAsset(context.assets, "fonts/Roboto/Roboto-Black.ttf"),
                    "Roboto-Medium" to Typeface.createFromAsset(context.assets, "fonts/Roboto/Roboto-Medium.ttf"),
                    "Roboto-Regular" to Typeface.createFromAsset(context.assets, "fonts/Roboto/Roboto-Regular.ttf"),

                    "RobotoCondensed-Bold" to Typeface.createFromAsset(context.assets, "fonts/RobotoCondensed/RobotoCondensed-Bold.ttf"),

                    "SFProDisplay-Bold" to Typeface.createFromAsset(context.assets, "fonts/SFProDisplay/SFProDisplay-Bold.otf"),
                    "SFProDisplay-Heavy" to Typeface.createFromAsset(context.assets, "fonts/SFProDisplay/SFProDisplay-Heavy.otf"),
                    "SFProDisplay-Medium" to Typeface.createFromAsset(context.assets, "fonts/SFProDisplay/SFProDisplay-Medium.otf"),
                    "SFProDisplay-Thin" to Typeface.createFromAsset(context.assets, "fonts/SFProDisplay/SFProDisplay-Thin.otf"),

                    "Signika-Bold" to Typeface.createFromAsset(context.assets, "fonts/Signika/Signika-Bold.otf"),
                    "Signika-Regular" to Typeface.createFromAsset(context.assets, "fonts/Signika/Signika-Regular.otf"),

                    "Ubuntu-Bold" to Typeface.createFromAsset(context.assets, "fonts/Ubuntu/Ubuntu-Bold.ttf"),
                    "Ubuntu-BoldItalic" to Typeface.createFromAsset(context.assets, "fonts/Ubuntu/Ubuntu-BoldItalic.ttf"),
                    "Ubuntu-Regular" to Typeface.createFromAsset(context.assets, "fonts/Ubuntu/Ubuntu-Regular.ttf"),
                )
            )
            Button(onClick = {
                dropDownMenuExpanded.value = !dropDownMenuExpanded.value
            }) {
                Text("Select a font")
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded.value,
                onDismissRequest = { dropDownMenuExpanded.value = false }
            ) {
                FontTypeAnim.values().forEach {
                    DropdownMenuItem(onClick = {
                        selectedFontName.value = it.type
                        fontChange(getFonts(it))
                        dropDownMenuExpanded.value = false
                    }) {
                        Text(it.type)
                    }
                }
            }
        }
    }

    private fun fontChange(fonts: Map<String, String>?) {
        val animationJson = context.assets.readAssetsFile("animations/BALI-PLANE.json")
        val animationRulesJson = context.assets.readAssetsFile("animations/BALI-PLANE-rules.json")

        val animationTransformer = KPAnimationTransformer()
        val result = animationTransformer.transform(
            animationJson,
            animationRulesJson,
            listOf("Text1", "Text2", "Text3", "Text4"),
            fonts
        )

        jsonString.value = result ?: throw (Exception("Error: Cannot transform the json file."))
        Log.i("Orpheus","Result: $result")
    }

    private fun getFonts(type: FontTypeAnim): Map<String, String> {
        return when (type) {
            FontTypeAnim.AbhayaLibre -> mapOf(
                "textBoldItalic" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "titleBlackItalic" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "textBlackItalic" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "textBold" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "titleBoldItalic" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "titleBold" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "textBlack" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "text" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf",
                "title" to "fonts/AbhayaLibre/AbhayaLibre-ExtraBold.ttf"
            )
            FontTypeAnim.Arial -> mapOf(
                "textBoldItalic" to "fonts/Arial/Arial.ttf",
                "titleBlackItalic" to "fonts/Arial/Arial.ttf",
                "textBlackItalic" to "fonts/Arial/Arial.ttf",
                "textBold" to "fonts/Arial/Arial.ttf",
                "titleBoldItalic" to "fonts/Arial/Arial.ttf",
                "titleBold" to "fonts/Arial/Arial.ttf",
                "textBlack" to "fonts/Arial/Arial.ttf",
                "text" to "fonts/Arial/Arial.ttf",
                "title" to "fonts/Arial/Arial.ttf",
            )
            FontTypeAnim.BebasNeue -> mapOf(
                "textBoldItalic" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "titleBlackItalic" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "textBlackItalic" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "textBold" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "titleBoldItalic" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "titleBold" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "textBlack" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "text" to "fonts/BebasNeue/BebasNeue-Bold.otf",
                "title" to "fonts/BebasNeue/BebasNeue-Bold.otf",
            )
            FontTypeAnim.Roboto -> mapOf(
                "textBoldItalic" to "fonts/Roboto/Roboto-Regular.ttf",
                "titleBlackItalic" to "fonts/Roboto/Roboto-Regular.ttf",
                "textBlackItalic" to "fonts/Roboto/Roboto-Regular.ttf",
                "textBold" to "fonts/Roboto/Roboto-Regular.ttf",
                "titleBoldItalic" to "fonts/Roboto/Roboto-Regular.ttf",
                "titleBold" to "fonts/Roboto/Roboto-Regular.ttf",
                "textBlack" to "fonts/Roboto/Roboto-Regular.ttf",
                "text" to "fonts/Roboto/Roboto-Regular.ttf",
                "title" to "fonts/Roboto/Roboto-Regular.ttf",
            )
        }
    }


}

fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }

@Preview
@Composable
fun FontTransformerPreview() {
    MyApplicationTheme {
        FontTransformer(LocalContext.current).FontTransformerView()
    }
}