import React from 'react';
import logo from './logo.svg';
import './App.css';
const animationTransformer = require('./script/kmp_lib')
function App() {

  const example = new animationTransformer.io.kannelle.testkmp.AnimationTransformerExample();
  const text = example.animText()
  const animJson = example.animJsonString(1)

  return (
    <div className="App">
      <header className="App-header">
        {text}
        <br></br>
        <br></br>
        <br></br>
        {animJson}
      </header>
    </div>
  );
}

export default App;
