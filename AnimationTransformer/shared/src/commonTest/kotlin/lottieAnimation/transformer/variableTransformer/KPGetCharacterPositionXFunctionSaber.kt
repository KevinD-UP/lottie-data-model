package lottieAnimation.transformer.variableTransformer

import kotlin.test.Test

class KPGetCharacterPositionXFunctionSaber {

    // SEATTLE
    @Test
    fun testVariableTransformerSeattlePlane() {
        val sut = setupSUT("SEATTLE-PLANE")

        //var0
        val textLayer7 = sut.layers[6]
        assertTextLayerPositionKeyframeStartY(textLayer7, 0, 980.0)
        assertTextLayerPositionKeyframeEndY(textLayer7, 0, 980.0)

        assertTextLayerPositionKeyframeStartY(textLayer7, 1, 980.0)
        assertTextLayerPositionKeyframeEndY(textLayer7, 1, 980.0)

        assertTextLayerPositionKeyframeStartY(textLayer7, 2, 980.0)
        assertTextLayerPositionKeyframeEndY(textLayer7, 2, 980.0)

        //var1
        val shapeLayer6 = sut.layers[5]
        assertShapeLayerRectHeight(shapeLayer6, 0, 0, 119.0)
        val shapeLayer8 = sut.layers[7]
        assertShapeLayerRectHeight(shapeLayer8, 0,0, 119.0)
        //var2
        val shapeLayer3 = sut.layers[2]
        assertShapeLayerRectWidth(shapeLayer3,0,0,697.0)
        val shapeLayer5 = sut.layers[4]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 697.0)
        assertShapeLayerRectWidth(shapeLayer6,0,0,697.0)
        assertShapeLayerRectWidth(shapeLayer8, 0,0,697.0)
        //var3
        assertShapeLayerPositionY(shapeLayer6,956.5)
        assertShapeLayerPositionY(shapeLayer8,956.5)
        //var4
        assertShapeLayerRectHeight(shapeLayer3,0, 0,160.0)
        assertShapeLayerRectHeight(shapeLayer5,0,0,160.0)
        //var5
        val textLayer4 = sut.layers[3]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 848.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 848.0)

        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 848.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 848.0)

        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 848.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 848.0)
        //var6
        assertShapeLayerPositionY(shapeLayer3,817.0)
        assertShapeLayerPositionY(shapeLayer5, 817.0)
        //var7
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 436.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 436.0)

        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 436.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 436.0)

        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 436.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 436.0)
        //var8
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 837.0)

        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 837.0)

        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 837.0)

        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 837.0)
    }

    @Test
    fun testVariableTransformerSeattlePeugeot() {
        val sut = setupSUT("SEATTLE-PEUGEOT")

        //var0
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,316.5)
        //var1
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer2,0,856.5)
    }

    @Test
    fun testVariableTransformerSeattleFord() {
        val sut = setupSUT("SEATTLE-FORD")

        //var0
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,579.0)

        assertTextLayerPositionKeyframeStartY(textLayer1,1,579.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 579.0)

        assertTextLayerPositionKeyframeStartY(textLayer1,2,579.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 579.0)
    }

    // LOS ANGELES
}