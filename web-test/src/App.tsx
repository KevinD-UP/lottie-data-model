import React from 'react';
import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import Home from "./Home";
import Visualiser from "./Visualiser";

function App() {
    return (
        <div className="container">
        <nav>
            <ul>
                <Link to="/">
                    Home
                </Link>
                <Link to="/visualiser">
                    Visualiser
                </Link>
            </ul>
        </nav>

        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/visualiser" element={<Visualiser />} />
        </Routes>
        </div>
    );
}

export default App;
