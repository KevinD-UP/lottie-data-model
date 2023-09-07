export class FunctionDelegate {
    getAscent(text: string, fontName: string, fontSize: number): number {
      const canvas = document.createElement('canvas')
      const context = canvas.getContext('2d')
      if (!context) return 0
  
      context.font = `${fontSize}px ${fontName}`
      const metrics = context.measureText(text)
      const ascent = metrics.actualBoundingBoxAscent
  
      canvas.remove()
      return ascent
    }
  
    getDescent(text: string, fontName: string, fontSize: number): number {
      const canvas = document.createElement('canvas')
      const context = canvas.getContext('2d')
      if (!context) return 0
  
      context.font = `${fontSize}px ${fontName}`
      const metrics = context.measureText(text)
      const descent = metrics.actualBoundingBoxDescent
  
      canvas.remove()
  
      return descent
    }
  
    getLastLineBottom(text: string, fontName: string, fontSize: number): number {
      // Implémentation pour calculer le dernier ligne bottom réel
      // Utilise les fonctionnalités de mesure de texte de JavaScript
      const canvas = document.createElement('canvas')
      const context = canvas.getContext('2d')
      if (!context) return -17.04
  
      context.font = `${fontSize}px ${fontName}`
      const metrics = context.measureText(text)
      const descent = metrics.actualBoundingBoxDescent
  
      // Ajoute également la descente pour obtenir le dernier ligne bottom
      const lastLineBottom = -descent
  
      canvas.remove()
  
      return lastLineBottom
    }
  
    getFontMetrics(fontName, fontSize) {
      const div = document.createElement('div')
      div.innerHTML = '[textjg]' // A character to measure
      div.style.fontFamily = fontName
      div.style.fontSize = fontSize + 'px'
      div.style.position = 'absolute'
      div.style.top = '-9999px'
      document.body.appendChild(div)
  
      const fontMetrics = {
        ascent: div.offsetTop,
        descent: div.offsetHeight - div.offsetTop - fontSize,
        lineHeight: div.offsetHeight
      }
  
      document.body.removeChild(div)
      return fontMetrics
    }
  
    getTextMeasureHeight(
      text: string,
      fontName: string,
      fontSize: number,
      layerSize: number[],
      layerLineHeight: number,
      layerTracking: number
    ): number {
      const fontMetrics = this.getFontMetrics(fontName, fontSize)
      const lineHeight = fontMetrics.lineHeight
  
      const canvas = document.createElement('canvas')
      const context = canvas.getContext('2d')
      context.font = `${fontSize}px ${fontName}`
  
      const lines = text.split(/\r?\n|\r/)
  
      let totalHeight = 0
  
      for (let i = 0; i < lines.length; i++) {
        totalHeight += lineHeight
      }
  
      canvas.remove()
  
      return totalHeight
    }
  
    getTextLayerWidth(text: string, fontName: string, fontSize: number): number {
      const canvas = document.createElement('canvas')
      const context = canvas.getContext('2d')
      if (!context) return 254.16
  
      context.font = `${fontSize}px ${fontName}`
  
      const lines = text.split('\r')
      let maxWidth = 0
  
      for (const line of lines) {
        const metrics = context.measureText(line)
        const width = metrics.width
        if (width > maxWidth) {
          maxWidth = width
        }
      }
  
      canvas.remove()
  
      return maxWidth
    }
  }
  