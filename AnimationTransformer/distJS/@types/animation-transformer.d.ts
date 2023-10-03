declare module "@pitchy-team/animation-transformer" {
  namespace transformer {
    interface FontModel {
      fontName: string;
      textAlign: string | null;
    }

    interface Font {
      font: string;
    }

    interface Colors {
      color1: string;
      color2: string;
      color3: string;
      color4: string;
      color5: string;
    }

    interface Scale {
      width: string;
      height: string;
      depth: string | null;
    }

    interface AnimationSize {
      width: string;
      height: string;
      // Add other properties as needed
    }

    interface Effects {
      panelX: string | null;
      panelY: string | null;
      bannerMarginWidth: string | null;
      bannerMarginHeight: string | null;
      // Add other properties as needed
    }

    interface KPAnimationTransformerFunctionsDelegate {
      getAscent(text: string, fontName: string, fontSize: number): number;
      getDescent(text: string, fontName: string, fontSize: number): number;
      getLastLineBottom(
        text: string,
        fontName: string,
        fontSize: number
      ): number;
      getTextMeasureHeight(
        text: string,
        fontName: string,
        fontSize: number,
        layerSize: number[] | null,
        layerLineHeight: number,
        layerTracking: number
      ): number;
      getTextLayerWidth(
        text: string,
        fontName: string,
        fontSize: number
      ): number;
    }

    class KPAnimationTransformerJs {
      constructor(functionsDelegate: KPAnimationTransformerFunctionsDelegate);

      /* Transform an animation */
      transformJs(
        lottieJsonString: string,
        texts?: string[] | null,
        fontsJson?: Font | null,
        fontsModelsJson?: FontModel | null,
        colorsJson?: Colors | null,
        scaleJson?: Scale | null,
        sizeJson?: AnimationSize | null,
        effectsJson?: Effects | null
      ): string | null;

      /* Return a JSON string representing width and height of an animation */
      getAnimationSize(
        lottieJsonString: string
      ): string | null;

      /* Return a JSON string representing the position of an animation in the properties panelX and panelY */
      getAnimationPosition(
        lottieJsonString: string
      ): string | null;

      /* Return a JSON string representing the scale of an animation */
      getScale(
        lottieJsonString: string
      ): string | null;
    }
  }

  // Export the transformer namespace
  export { transformer };
}
