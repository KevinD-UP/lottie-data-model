package io.kannelle.testkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.kannelle.testkmp.AnimationTransformerExample
import io.kannelle.testkmp.android.presentation.ColorTransformer
import io.kannelle.testkmp.android.presentation.FontTransformer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                Column {
                                    Button(onClick = { navController.navigate("animation") }) {
                                        Text("Animation Demo")
                                    }
                                    Button(onClick = { navController.navigate("fontTransformer") }) {
                                        Text("FontTransformer Demo")
                                    }
                                    Button(onClick = { navController.navigate("Color Transformer") }) {
                                        Text("ColorTransformer Demo")
                                    }
                                }
                            }
                            composable("animation") {
                                Button(onClick = { navController.popBackStack() }) {
                                    Text("< Back")
                                }
                                GreetingView(AnimationTransformerExample().animText())
                            }
                            composable("fontTransformer") {
                                Button(onClick = { navController.popBackStack() }) {
                                    Text("< Back")
                                }
                                FontTransformer(context = this@MainActivity.applicationContext).FontTransformerView()
                            }
                            composable("Color Transformer") {
                                Button(onClick = { navController.popBackStack() }) {
                                    Text("< Back")
                                }
                                ColorTransformer(context = this@MainActivity.applicationContext).ColorTransformerView()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
