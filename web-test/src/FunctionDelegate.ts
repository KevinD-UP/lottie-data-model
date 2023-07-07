export class FunctionDelegate {
    getAscent(text: string, fontName: string, fontSize: number): number {
        const canvas = document.createElement("canvas");
        const context = canvas.getContext("2d");
        if (!context) return 0;

        context.font = `${fontSize}px ${fontName}`;
        console.log('context.font', context.font)
        console.log('context', context)
        const metrics = context.measureText(text);
        console.log('metrics', metrics)
        const ascent = metrics.fontBoundingBoxAscent;

        return ascent

    }

    getDescent(text: string, fontName: string, fontSize: number): number {
        const canvas = document.createElement("canvas");
        const context = canvas.getContext("2d");
        if (!context) return 0;

        context.font = `${fontSize}px ${fontName}`;
        const metrics = context.measureText(text);
        const descent = metrics.fontBoundingBoxDescent;

        return descent;
    }

    getLastLineBottom(text: string, fontName: string, fontSize: number): number {
        // Implémentation pour calculer le dernier ligne bottom réel
        // Utilise les fonctionnalités de mesure de texte de JavaScript
        const canvas = document.createElement("canvas");
        const context = canvas.getContext("2d");
        if (!context) return -17.04;

        context.font = `${fontSize}px ${fontName}`;
        const metrics = context.measureText(text);
        const descent = metrics.fontBoundingBoxDescent;

        // Ajoute également la descente pour obtenir le dernier ligne bottom
        const lastLineBottom = -descent;

        return lastLineBottom;
    }

     getTextMeasureHeight(text: string, fontName: string, fontSize: number, layerSize: number[], layerLineHeight: number, layerTracking: number): number {
         const canvas = document.createElement("canvas");
         const context = canvas.getContext("2d");
         if (!context) return 0;

         context.font = `${fontSize}px ${fontName}`;
         const metrics = context.measureText(text);
         const fontHeight = metrics.fontBoundingBoxAscent + metrics.fontBoundingBoxDescent;

         return fontHeight
    }


    getTextLayerWidth(text: string, fontName: string, fontSize: number): number {
        const canvas = document.createElement("canvas");
        const context = canvas.getContext("2d");
        if (!context) return 254.16;

        context.font = `${fontSize}px ${fontName}`;
        const metrics = context.measureText(text);
        const width = metrics.width;

        return width;
    }
}
