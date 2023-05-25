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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import io.kannelle.testkmp.android.*
import transformer.KPAnimationTransformer

class ColorTransformer(
    private val context: Context
) {

    private val jsonString: MutableState<String> = mutableStateOf(context.assets.readAssetsFile("animations/BALI-PLANE.json"))

    private val animationShown: MutableState<Boolean> = mutableStateOf(false)
    private val currentAnimation: MutableState<String> = mutableStateOf(AnimationType.BALI_PLANE.name)

    private val themeShown: MutableState<Boolean> = mutableStateOf(false)
    private val currentTheme: MutableState<String> = mutableStateOf(ColorThemeType.ACCOR.name)

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
                Text(text = "Animation :")
                Spacer(modifier = Modifier.weight(1.0F))
                Button(onClick = {
                    animationShown.value = !animationShown.value
                }) {
                    Text(currentAnimation.value)
                    DropdownMenu(
                        expanded = animationShown.value,
                        onDismissRequest = { animationShown.value = false }
                    ) {
                        AnimationType.values().forEach {
                            DropdownMenuItem(onClick = {
                                currentAnimation.value = it.value
                                animationShown.value = false
                                colorChanged(getColors())
                            }) {
                                Text(it.name)
                            }
                        }
                    }
                }
            }
            Row(
                Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Theme:")
                Spacer(modifier = Modifier.weight(1.0F))
                Button(onClick = {
                    themeShown.value = !themeShown.value
                }) {
                    Text(currentTheme.value)
                    DropdownMenu(
                        expanded = themeShown.value,
                        onDismissRequest = { themeShown.value = false }
                    ) {
                        ColorThemeType.values().forEach {
                            DropdownMenuItem(onClick = {
                                currentTheme.value = it.name
                                themeShown.value = false
                                colorChanged(getColors())
                            }) {
                                Text(it.name)
                            }
                        }
                    }
                }

            }
            Divider()
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
            }) {
                Text("Transform!")
            }
        }
    }

    @Composable
    private fun ColorDropDownMenu(
        color: MutableState<Color>,
        colorShown: MutableState<Boolean>
    ) {
        Button(onClick = {
            colorShown.value = !colorShown.value
        }) {
            Text("Select a color")
            DropdownMenu(
                expanded = colorShown.value,
                onDismissRequest = { colorShown.value = false },
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
                                color.value = it
                                colorShown.value = false

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
        val animationJson = context.assets.readAssetsFile("animations/${currentAnimation.value}.json")
        val animationRulesJson = context.assets.readAssetsFile("animations/${currentAnimation.value}-rules.json")

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
        val themeName = currentAnimation.value.split("-").firstOrNull()?.lowercase()

        return when (ColorThemeType.fromValue(currentTheme.value)) {
            ColorThemeType.ACCOR -> colorSetAccor()[themeName]!!
            ColorThemeType.BTS -> colorSetBTS()[themeName]!!
            ColorThemeType.BLOCKCHAIN -> colorSetBlockchain()[themeName]!!
            null -> colorSetAccor()[themeName]!!
        }
    }
}

@Preview
@Composable
private fun ColorTransformerPreview() {
    MyApplicationTheme {
        ColorTransformer(LocalContext.current).ColorTransformerView()
    }
}