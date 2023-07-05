export class FunctionDelegate {
    getAscent(text: string, fontName: string, fontSize: number): number {
        return 0
    }

    getDescent(text: string, fontName: string, fontSize: number): number {
        return 0
    }

    getLastLineBottom(text: string, fontName: string, fontSize: number): number {
        return -17.04
    }

    getTextMeasureHeight(
        text: string,
        fontName: string,
        fontSize: number,
        layerSize: number[],
        layerLineHeight: number,
        layerTracking: number
    ): number {
        return 129.6
    }

    getTextLayerWidth(text: string, fontName: string, fontSize: number): number {
        return 254.16
    }
}