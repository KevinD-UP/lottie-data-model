import React, { useEffect }  from 'react';
import Lottie from "lottie-web"
import './App.css';
import {FunctionDelegate} from "./FunctionDelegate";
import * as animation from "./resource/BALI-FORD.json"
import * as rules from "./resource/BALI-FORD-rules.json"

const animationTransformerLib = require('./script/kmp_lib')

function App() {

    const example = new animationTransformerLib.io.kannelle.testkmp.AnimationTransformerExample();
    const animJson = example.animJsonString(1)

    console.log(animationTransformerLib)

    const animationTransformer = new animationTransformerLib.transformer.KPAnimationTransformer(new FunctionDelegate());
    console.log(animationTransformer)

    const lottieAnimationString: string = JSON.stringify(animation);
    const animationRulesString: string = JSON.stringify(rules);

    const colors = {
        text: '#FFFFF',
        shape: '#000000',
        background: '#000000'
    }

    const animTransformJson = animationTransformer.transformJs(lottieAnimationString, animationRulesString, null, null, colors)

    useEffect(() => {
        Lottie.loadAnimation({
            // @ts-ignore
            container: document.querySelector('#animation'),
            renderer: "svg",
            loop: true,
            autoplay: true,
            animationData: JSON.parse(animTransformJson),
        });
    }, [animTransformJson]);

  return (
    <div id={'animation'}></div>
  );
}

export default App;
