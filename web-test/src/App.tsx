import React, {useRef, useState} from 'react';
import Lottie from "lottie-web"
import './App.css';
import {FunctionDelegate} from "./FunctionDelegate";
import * as animation from "./resource/BALI-FORD.json"
import * as rules from "./resource/BALI-FORD-rules.json"

const animationTransformerLib = require('./script/kmp_lib')

function App() {
    const animationTransformer = new animationTransformerLib.transformer.KPAnimationTransformer(new FunctionDelegate());

    const animationContainerRef = useRef(null);
    const lottieAnimationString: string = JSON.stringify(animation);
    const animationRulesString: string = JSON.stringify(rules);

    const [textColor, setTextColor] = useState('#FFFFFF')
    const [shapeColor, setShapeColor] = useState('#000000')
    const [backgroundColor, setBackgroundColor] = useState('#000000')
    const [text, setText] = useState('Texte du Haut Texte du Bas')


    function transform() {
        const colors = {
            text: textColor,
            shape: shapeColor,
            background: backgroundColor
        }

        const font = {
            textBold: 'Lato-Bold'
        }

        const animationContainer = animationContainerRef.current;

        // Remove existing animation
        // @ts-ignore
        animationContainer.innerHTML = '';

        const animTransformJson = animationTransformer.transformJs(lottieAnimationString, animationRulesString, [text], font, colors)
        Lottie.loadAnimation({
            // @ts-ignore
            container: animationContainer,
            renderer: "svg",
            loop: true,
            autoplay: true,
            animationData: JSON.parse(animTransformJson),
        });
    }

    return (
    <>
        <div ref={animationContainerRef} id={'animation'}></div>
        <span>textColor</span>
        <input
            key={`textColor`}
            name="textColor"
            value={textColor}
            onChange={(e) => setTextColor(e.target.value)}
        />
        <br/>
        <span>shapeColor</span>
        <input
            key={`shapeColor`}
            name="shapeColor"
            value={shapeColor}
            onChange={(e) => setShapeColor(e.target.value)}
        />
        <br/>
        <span>backgroundColor</span>
        <input
            key={`backgroundColor`}
            name="backgroundColor"
            value={backgroundColor}
            onChange={(e) => setBackgroundColor(e.target.value)}
        />
        <br/>
        <span>text content</span>
        <input
            key={`text`}
            name="text"
            value={text}
            onChange={(e) => setText(e.target.value)}
        />
        <button
            onClick={() => transform()}
        >
            <span>transform</span>
        </button>
    </>
  );
}

export default App;
