import React, { useEffect }  from 'react';
import Lottie from "lottie-web"
import './App.css';

const animationTransformer = require('./script/kmp_lib')

function App() {

    const example = new animationTransformer.io.kannelle.testkmp.AnimationTransformerExample();
    const text = example.animText()
    const animJson = example.animJsonString(1)

    console.log(animationTransformer)

    useEffect(() => {
        Lottie.loadAnimation({
            // @ts-ignore
            container: document.querySelector('#animation'),
            renderer: "svg",
            loop: true,
            autoplay: true,
            animationData: JSON.parse(animJson),
        });
    }, [animJson]);

  return (
    <div id={'animation'}></div>
  );
}

export default App;
