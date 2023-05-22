package io.kannelle.testkmp.android.presentation

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import io.kannelle.testkmp.android.MyApplicationTheme
import io.kannelle.testkmp.android.mockAnimations
import transformer.KPAnimationTransformer

class ColorTransformer(
    private val context: Context
) {

    private val colors = listOf(
        Color.White,
        Color.Black,
        Color.Blue,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Magenta,
        Color.Red,
        Color.DarkGray,
    )
    private val jsonString: MutableState<String> = mutableStateOf(context.assets.readAssetsFile("animations/animation.json"))

    private val animations = mockAnimations()

    private val animationFamily: MutableState<String> = mutableStateOf("algiers")
    private val animationFamilyShown: MutableState<Boolean> = mutableStateOf(false)
    private val animation: MutableState<String> = mutableStateOf("animations/animation.json")
    private val animationShown: MutableState<Boolean> = mutableStateOf(false)
    private val animationRule: MutableState<String> = mutableStateOf("animations/animation-rules.json")
    private val animationRuleShown: MutableState<Boolean> = mutableStateOf(false)
    private val opacity: MutableState<Float> = mutableStateOf(1f)
    private val colorDropDownShown: MutableState<Boolean> = mutableStateOf(false)
    private val selectedColor: MutableState<Color> = mutableStateOf(Color.Black)

    @Composable
    fun ColorTransformerView() {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.JsonString(jsonString.value))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Animation Family:")
                Spacer(modifier = Modifier.weight(1.0F))
                Button(onClick = {
                    animationFamilyShown.value = !animationFamilyShown.value
                }) {
                    Text(animationFamily.value)
                    DropdownMenu(
                        expanded = animationFamilyShown.value,
                        onDismissRequest = { animationFamilyShown.value = false }
                    ) {
                        animations.forEach {
                            DropdownMenuItem(onClick = {
                                animationFamily.value = it.animationFamily
                                animationFamilyShown.value = false
                            }) {
                                Text(it.animationFamily)
                            }
                        }
                    }
                }
            }
            Row(
                Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Animation:")
                Spacer(modifier = Modifier.weight(1.0F))
                Button(onClick = {
                    animationShown.value = !animationShown.value
                }) {
                    Text(animation.value.substringAfterLast("/").substringBefore(".json"))
                    DropdownMenu(
                        expanded = animationShown.value,
                        onDismissRequest = { animationShown.value = false }
                    ) {
                        animations
                            .first { it.animationFamily == animationFamily.value }
                            .animationFiles
                            .forEach {
                                DropdownMenuItem(onClick = {
                                    animation.value = it
                                    animationShown.value = false
                                }) {
                                    Text(it.substringAfterLast("/").substringBefore(".json"))
                                }
                            }
                    }
                }

            }
            Row(
                Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Animation Rule:")
                Spacer(modifier = Modifier.weight(1.0F))
                Button(onClick = {
                    animationRuleShown.value = !animationRuleShown.value
                }) {
                    Text(animationRule.value.substringAfterLast("/").substringBefore(".json"))
                    DropdownMenu(
                        expanded = animationRuleShown.value,
                        onDismissRequest = { animationRuleShown.value = false }
                    ) {
                        animations
                            .first { it.animationFamily == animationFamily.value }
                            .animationRulesFiles
                            .forEach {
                                DropdownMenuItem(onClick = {
                                    animationRule.value = it
                                    animationRuleShown.value = false
                                }) {
                                    Text(it.substringAfterLast("/").substringBefore(".json"))
                                }
                            }
                    }
                }

            }
            Divider()
            Row(
                Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Text Color")
                Spacer(modifier = Modifier.weight(1.0F))
                ColorDropDownMenu()
            }
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "Opacity: ${opacity.value * 100}%",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Slider(
                    value = opacity.value,
                    onValueChange = { value ->
                        opacity.value = value
                    }
                )
            }
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
                colorChanged(getColors())
                Log.i("Orpheus", "Animation: ${animation.value}")
                Log.i("Orpheus", "Animation Rules: ${animationRule.value}")
                Log.i("Orpheus", "Color: ${selectedColor.value}")
                Log.i("Orpheus", "opacity: ${opacity.value}")
            }) {
                Text("Transform!")
            }
        }
    }

    @Composable
    private fun ColorDropDownMenu() {
        Button(onClick = {
            colorDropDownShown.value = !colorDropDownShown.value
        }) {
            Text("Select a color")
            DropdownMenu(
                expanded = colorDropDownShown.value,
                onDismissRequest = { colorDropDownShown.value = false },
                modifier = Modifier
                    .width(300.dp)
                    .height(500.dp)
            ) {
                Surface(color = Color.Gray) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .height(500.dp)
                            .width(300.dp)
                    ) {
                        items(colors) {
                            DropdownMenuItem(onClick = {
                                selectedColor.value = it
                                colorDropDownShown.value = false
                                colorChanged(getColors())
                            }) {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(it)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun colorChanged(colors: Map<String, String>) {
        val animationJson = context.assets.readAssetsFile(animation.value)
        val animationRulesJson = context.assets.readAssetsFile(animationRule.value)

        val animationTransformer = KPAnimationTransformer()
        val result = animationTransformer.transform(
            animationJson,
            animationRulesJson,
            listOf("Text1", "Text2", "Text3", "Text4"),
            null,
            colors
        )

        jsonString.value = result ?: throw (Exception("Error: Cannot transform the json file."))
        Log.i("Orpheus", "Result: $result")
    }

    private fun getColors(): Map<String, String> {
        return when (animationFamily.value) {
            "bogota" -> mapOf(
                "shapeNeutral" to selectedColor.value.toHexCode(),
                "figure" to selectedColor.value.toHexCode(),
                "shapeShadow" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "shapeSecondary" to selectedColor.value.toHexCode(),
                "backgroundOpacity" to "0.3",
                "text" to selectedColor.value.toHexCode(),

                "shapeMain" to "#FB6B00",
                "shapeShadowOpacity" to "0.80"
            )
            "berlin" -> mapOf(
                "gradientTop" to selectedColor.value.toHexCode(),
                "neutralGradientTop" to selectedColor.value.toHexCode(),
                "titleGradientTop" to selectedColor.value.toHexCode(),
                "textBackgroundOpacity" to "0.7",
                "neutralGradientBottom" to selectedColor.value.toHexCode(),
                "shapeSecond" to selectedColor.value.toHexCode(),

                "shapeNeutral" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "titleGradientBottom" to selectedColor.value.toHexCode(),
                "slideBackground" to selectedColor.value.toHexCode(),
                "titleBackgroundOpacity" to "0.7",
                "textBackground" to selectedColor.value.toHexCode(),
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "text" to selectedColor.value.toHexCode(),
                "gradientBottom" to selectedColor.value.toHexCode(),
                "shapeMain" to selectedColor.value.toHexCode(),
                "titleBackground" to selectedColor.value.toHexCode()
            )
            "geneva" -> mapOf(
                "container" to selectedColor.value.toHexCode(),
                "shadow" to selectedColor.value.toHexCode(),
                "shape" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "line" to selectedColor.value.toHexCode(),
                "titleText" to selectedColor.value.toHexCode(),
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "text" to selectedColor.value.toHexCode(),
                "shadowOpacity" to "0.7",
                "titleBackground" to selectedColor.value.toHexCode()
            )
            "losAngeles" -> mapOf(
                "shadow" to selectedColor.value.toHexCode(),
                "slideTextStrong" to selectedColor.value.toHexCode(),
                "textStrong" to selectedColor.value.toHexCode(),
                "subtitleBottomText" to selectedColor.value.toHexCode(),
                "shadowOpacity" to "1",
                "subtitleTopText" to selectedColor.value.toHexCode(),
                "containerStrong" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "titleText" to selectedColor.value.toHexCode(),
                "slideBackground" to selectedColor.value.toHexCode(),
                "slideText" to selectedColor.value.toHexCode(),
                "floatingBackground" to selectedColor.value.toHexCode(),
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "floatingText" to selectedColor.value.toHexCode(),
                "text" to selectedColor.value.toHexCode(),
                "subtitleBackground" to selectedColor.value.toHexCode(),
                "titleBackground" to selectedColor.value.toHexCode()
            )
            "seattle" -> mapOf(
                "subtitleBottomBackground" to selectedColor.value.toHexCode(),
                "shape" to selectedColor.value.toHexCode(),
                "slideFullBackground" to selectedColor.value.toHexCode(),
                "slideSplitBottomText" to selectedColor.value.toHexCode(),
                "subtitleBottomText" to selectedColor.value.toHexCode(),
                "main" to selectedColor.value.toHexCode(),
                "slideSplitBottomBackground" to selectedColor.value.toHexCode(),
                "subtitleTopText" to selectedColor.value.toHexCode(),

                "second" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "slideSplitTopText" to selectedColor.value.toHexCode(),
                "slideFullText" to selectedColor.value.toHexCode(),
                "titleText" to selectedColor.value.toHexCode(),
                "floatingBackground" to selectedColor.value.toHexCode(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "text" to selectedColor.value.toHexCode(),
                "slideSplitTopBackground" to selectedColor.value.toHexCode(),
                "floatingText" to selectedColor.value.toHexCode(),
                "titleBackground" to selectedColor.value.toHexCode(),
                "subtitleTopBackground" to selectedColor.value.toHexCode()
            )
            "bali" -> mapOf(
                "container" to selectedColor.value.toHexCode(),
                "shape" to selectedColor.value.toHexCode(),
                "line" to selectedColor.value.toHexCode(),
                "backgroundOpacity" to "0.5",
                "background" to selectedColor.value.toHexCode(),
                "titleText" to selectedColor.value.toHexCode(),
                "titleBackgroundOpacity" to "0.85",
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "text" to "#ffffff",
                "subtitleOpacity" to "0.85",
                "titleBackground" to selectedColor.value.toHexCode()
            )
            "algiers" -> mapOf(
                "subtitleBottomBackground" to selectedColor.value.toHexCode(),
                "containerBottom" to selectedColor.value.toHexCode(),
                "slideSplitBottomText" to selectedColor.value.toHexCode(),
                "subtitleBottomText" to selectedColor.value.toHexCode(),
                "slideSplitBottomBackground" to selectedColor.value.toHexCode(),
                "gradientRight" to selectedColor.value.toHexCode(),
                "subtitleTopText" to selectedColor.value.toHexCode(),
                "gradientText" to selectedColor.value.toHexCode(),
                "containerTop" to selectedColor.value.toHexCode(),
                "slideSplitTopText" to selectedColor.value.toHexCode(),
                "background" to selectedColor.value.toHexCode(),
                "titleText" to selectedColor.value.toHexCode(),
                "titleBackgroundOpacity" to opacity.value.toString(),
                "maskGradientTop" to selectedColor.value.toHexCode(),
                "maskGradientBottom" to selectedColor.value.toHexCode(),
                "text" to selectedColor.value.toHexCode(),
                "gradientLeft" to selectedColor.value.toHexCode(),
                "slideSplitTopBackground" to selectedColor.value.toHexCode(),
                "subtitleOpacity" to opacity.value.toString(),
                "titleBackground" to selectedColor.value.toHexCode(),
                "subtitleTopBackground" to selectedColor.value.toHexCode()
            )
            else -> throw (Exception("Unhandled theme? ${animationFamily.value}"))
        }
    }

    private fun Color.toHexCode(): String {
        val red = this.red * 255
        val green = this.green * 255
        val blue = this.blue * 255
        return String.format("#%02x%02x%02x", red.toInt(), green.toInt(), blue.toInt())
    }

    private fun Color.toHexCodeWithAlpha(): String {
        val alpha = this.alpha * 255
        val red = this.red * 255
        val green = this.green * 255
        val blue = this.blue * 255
        return String.format("#%02x%02x%02x%02x", alpha.toInt(), red.toInt(), green.toInt(), blue.toInt())
    }
}

@Preview
@Composable
private fun ColorTransformerPreview() {
    MyApplicationTheme {
        ColorTransformer(LocalContext.current).ColorTransformerView()
    }
}