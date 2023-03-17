package lottieAnimation.layer

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.FunSpec
import kotlinx.serialization.json.Json
import lottieAnimation.layer.serializers.LayerListSerializer
import lottieAnimation.layer.serializers.LayerSerializer

class LayerTest: FunSpec({
    val jsonsToTest = mapOf(
        "Precomp Layer" to """{ "ty": 0, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "sy": { "sysy": "sysy" }, "refId": "precompLayerId", "w": 1920, "h": 1080 }""",
        "Solid Color Layer" to """{ "ty": 1, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "sy": { "sysy": "sysy" }, "sc": "#001122", "sh": 1080, "sw": 1920 }""",
        "Image Layer" to """{ "ty": 2, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "sy": { "sysy": "sysy" }, "refId": "imageId" }""",
        "Null Layer" to """{ "ty": 3, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "sy": { "sysy": "sysy" } }""",
        "Shape Layer" to """{ "ty": 4, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "sy": { "sysy": "sysy" }, "shapes": [] }""",
        "Text Layer" to """
{ "ty": 5, "nm": "nanana", "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "tt": {}, "t": {
"a": [], "d": {
    "k": [{
        "t": 0, "s": {
            "f": "ArialBlack",
            "fc": [0, 0, 1],
            "t": "AMAZING TEXT"
        }
    }]
}, "m": {}, "p": {}
} }
        """,
        "Audio Layer" to """{ "ty": 6, "ddd": 1, "ip": 7, "op": 8, "st": 9, "au": {}, "refId": "audioId" }""",
        "Camera Layer" to """{ "ty": 13, "ddd": 1, "ip": 7, "op": 8, "st": 9, "ks": {}, "pe": {} }""",
        "Data Layer" to """{ "ty": 15, "ddd": 1, "ip": 7, "op": 8, "st": 9, "refId": "dataSourceId" }"""
    )

    jsonsToTest.forEach { layerName, layerJson ->
        test("serialiaze/deserialize the '$layerName'") {
            val layer = Json.decodeFromString(LayerSerializer, layerJson)
            println(layer)
            val newJson = Json {
                explicitNulls = false
                encodeDefaults = true
            }.encodeToString(LayerSerializer, layer)
            println(newJson)
            newJson.shouldEqualJson(layerJson)
        }
    }

    test("it can handle a list of layers of different types") {
        val json = "[" + jsonsToTest["Image Layer"] + "," + jsonsToTest["Data Layer"] + "]"

        val layers = Json.decodeFromString(LayerListSerializer, json)


        println(layers)
    }
})