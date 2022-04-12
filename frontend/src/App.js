import logo from './logo.svg';
import './App.css';
<<<<<<< HEAD
import { Calendar } from './components/form/calendar';
import {ExampleForm} from './components/form'
import { useState } from 'react';



function App() {
  const [state,setState] = useState(new Set([1,2]))
  const availableSet = new Set([1,6,2,5]);
  var termsInfo= {
    headers: ["Monday","Tuesday","Wednesday","Thursday","Friday"],
    rows: [{label:"8:00-9:30",cells:[{ id: 1},{id:-1},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 10},{id:2},{id:-1},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:-1},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]}]
}
  return (
    <><Calendar termsInfo={termsInfo} availableTermsSet = {availableSet} selectedTerms ={state} toggleTerm={(id)=>{
      const newSet = new Set(state.values())
      if (newSet.has(id)){
        newSet.delete(id);
            }
            else{
                newSet.add(id);
             }
      setState(newSet)
    }}/></>
=======

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
>>>>>>> parent of 548d9dd... Add Calendar components
  );
}

export default App;
