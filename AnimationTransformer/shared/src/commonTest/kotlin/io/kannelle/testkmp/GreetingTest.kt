package io.kannelle.testkmp

import kotlinx.serialization.json.*
import kotlin.test.*

class testTest {
    @Test
    fun test() {
        val json = """
            {
                "level1": {
                    "level2": {
                        "level21": "value3",
                        "level22": {
                            "value4_1": "test41",
                            "value4_2": "test42"
                         }
                     }
                }
            }
        """.trimIndent()

        val jsonElement = Json.parseToJsonElement(json)
        val jsonObject = jsonElement.jsonObject
        val sutLevel3Value = getValueUsingKeyString(jsonObject = jsonObject, keyString = "level1.level2.level21")
        assertEquals("value3", sutLevel3Value)

        val sutLevel4BeforeUpdate = getValueUsingKeyString(jsonObject = jsonObject, keyString = "level1.level2.level22.value4_1")
        assertEquals("test41", sutLevel4BeforeUpdate)

        val newLevel4Json = """
            {
                "value4_1": "new41",
                "value4_2": "new42"
            }
        """.trimIndent()
        val newLevel4JsonElement = Json.parseToJsonElement(newLevel4Json)
        val newJsonObject = setValueUsingKeyString(jsonObject = jsonObject, value = newLevel4JsonElement.jsonObject, keyString = "level1.level2.level22")
        println("newJsonObject[1]= $newJsonObject")
        val newJson = setValueUsingKeyString(jsonElement = jsonElement, keyString = "level1.level2.level22", newValue = newLevel4JsonElement)
        println("newJsonObject[2]= $newJson")
        val sutLevel4AfterUpdate =  getValueUsingKeyString(jsonObject = newJsonObject, keyString = "level1.level2.level22.level4_1")
        assertEquals("new41", sutLevel4AfterUpdate)
    }

    fun getValueUsingKeyString(jsonObject: JsonObject?, keyString: String): String? {
        val keys = keyString.split(".")
        var currentObject: JsonObject? = jsonObject

        for (i in 0 until keys.size - 1) {
            currentObject = currentObject?.get(keys[i])?.jsonObject
        }

        return currentObject?.get(keys.last())?.jsonPrimitive?.content
    }

    fun setValueUsingKeyString(jsonObject: JsonObject?, value: JsonObject, keyString: String): JsonObject? {
        val keys = keyString.split(".")
        val jsonObject = jsonObject ?: return null
        var result: JsonObject? = JsonObject(jsonObject.toMutableMap()).apply {
            var currentObject: JsonObject? = this
            for (i in 0 until keys.size - 1) {
                currentObject = currentObject?.get(keys[i])?.jsonObject
            }
            println("update $currentObject")
            currentObject = value
        }
        return result
    }

    fun setValueUsingKeyString(jsonElement: JsonElement?, keyString: String, newValue: JsonElement): JsonObject? {
        val keys = keyString.split(".")
        var currentElement: JsonElement? = jsonElement

        // Iterate until the second last key
        for (i in 0 until keys.size - 1) {
            currentElement = currentElement?.jsonObject?.get(keys[i])
        }
/*
        // Replace the last key with the new value
        val lastKey = keys.last()
        if (currentElement is JsonObject) {
            val mutableMap = currentElement.toMutableMap()
            mutableMap[lastKey] = newValue
            return JsonObject(mutableMap)
        }


 */
        val jsonObject = jsonElement?.jsonObject ?: return null
        val newJsonObj = JsonObject(jsonObject.toMutableMap().apply {
            var currentMap: MutableMap<String, JsonElement>? = this
            for (i in 0 until keys.size - 1) {
                currentMap = (currentMap?.get(key = keys[i]))?.jsonObject?.toMutableMap()
            }
            val lastKey = keys.last()
            val tempMap = currentMap ?: return null
            currentMap[lastKey] = newValue
        })

        return newJsonObj
    }
}