import React, {useEffect, useRef, useState} from 'react'
import Lottie from "lottie-web"
import './App.css';
import {FunctionDelegate} from "./FunctionDelegate";
import {animations, rules} from "./Fixture";
import * as animation from "./resource/ALGIERS-FORD.json"
import * as animationRules from "./resource/ALGIERS-FORD-rules.json"

const animationTransformerLib = require('./script/kmp_lib')

function Visualiser(){

    const animationTransformer = new animationTransformerLib.transformer.KPAnimationTransformer(new FunctionDelegate());

    const animationContainerRef = useRef(null);
    
    const [animationLottieJsonName, setAnimationLottieJson] = useState("ALGIERS-FORD");
    const [animationRulesJsonName, setAnimationRulesJson] = useState("ALGIERS-FORD-rules");
    const [animationLottieJson, setAnimationLottieJsonData] = useState(JSON.stringify(animation));
    const [animationRulesJson, setAnimationRulesJsonData] = useState(JSON.stringify(animationRules));

    useEffect(() => {
        transform();
    }, [animationLottieJson, animationRulesJson]);

    async function transform(){
        await fetch(`resource/${animationLottieJsonName}.json`)
            .then(response => response.json())
            .then(data => setAnimationLottieJsonData(JSON.stringify(data)))
            .catch(error => console.error(error));
        await fetch(`resource/${animationRulesJsonName}.json`)
            .then(response => response.json())
            .then(data => setAnimationRulesJsonData(JSON.stringify(data)))
            .catch(error => console.error(error));

        const animationContainer = animationContainerRef.current;

        // Remove existing animation
        // @ts-ignore
        animationContainer.innerHTML = '';

        const animTransformJson = animationTransformer.transformJs(animationLottieJson, animationRulesJson)
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
            <span> Animation </span>
            <select value={animationLottieJsonName} onChange={(e) => setAnimationLottieJson(e.target.value)}>
                {animations.map((animation) =>
                    <option value={animation}>{animation}</option>
                )}
            </select>
            <span> Rules </span>
            <select value={animationRulesJsonName} onChange={(e) => setAnimationRulesJson(e.target.value)}>
                {rules.map((rule) =>
                    <option value={rule}>{rule}</option>
                )}
            </select>
            <button
                onClick={() => transform()}
            >
                <span>transform</span>
            </button>
            <div ref={animationContainerRef} id={'animation'}></div>
        </>
    );
}

export default Visualiser