# @pitchy-team/animation-transformer

## Description
@pitchy-team/animation-transformer is an NPM package that can be used to customize a Lottie animation. The package is partially the result of the transpilation of a tool written in Kotlin that has been created by Kannelle.
A Lottie animation can be customized given a set of rules - the animation rules for the Lottie animations - and a set of values for instance a set of colors and fonts that should be used in the customized Lottie animation.

## Installation
First, configure your local installation : 
```bash
npm config set @pitchy-team:registry https://gitlab.com/api/v4/projects/32771828/packages/npm/
npm config set -- //gitlab.com/api/v4/projects/32771828/packages/npm/:_authToken=${GITLAB_TOKEN}
```
Replace GITLAB_TOKEN with your own token that you can create here : https://gitlab.com/-/profile/personal_access_tokens

You can now install this package using npm :
```
npm install @pitchy-team/animation-transformer
```

## Usage
Here's an example of how to use @pitchy-team/animation-transformer in your project :

```Typescript
import { transformer } from '@pitchy-team/animation-transformer/'
import { FunctionDelegate } from '@pitchy-team/animation-transformer/FunctionDelegate'

const animationTransformer = new transformer.KPAnimationTransformerJs(new FunctionDelegate())

const colors = {
  color1: textColor,
  color2: textColor,
  color3: textColor,
  color4: textColor,
  color5: textColor,
  background: backgroundColor
}

const fonts = {
  font: 'Poppins-Bold'
}

const fontsModels = {
  font: { name: 'Poppins-Bold', textAlign: textAlign }
}

const scaleformat = { width: scale, height: scale, depth: '100' }

const size = { width: '1920', height: '1080' }

const texts = text.replace(/\n/g, '\r')

const effects = {
  bannerMarginWidth: marginWidth,
  bannerMarginHeight: marginHeight
}

const animationTransformed = animationTransformer.transformJs(
  lottieAnimationString,
  animationRulesString,
  [texts],
  fonts,
  fontsModels,
  colors,
  scaleformat,
  size,
  effects
)
```

## Module Structure 

Here's an overview of the module structure:

- FontModel: Represents font information including name and text alignment.
- Font: Represents font styling.
- Colors: Defines color options.
- Scale: Specifies scaling properties.
- AnimationSize: Defines animation size properties.
- Effects: Specifies animation effects.
- KPAnimationTransformerFunctionsDelegate: Provides functions for text measurement and layout.
- KPAnimationTransformerJs: The main class for transforming animations and text.