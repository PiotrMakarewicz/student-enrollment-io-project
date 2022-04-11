import logo from './logo.svg';
import './App.css';
import Calendar from './components/form/calendar/Calendar';
import {ExampleForm} from './components/form'
import { useState } from 'react';



function App() {
  const [state,setState] = useState(new Set())
  var availableTerms= {
    headers: ["Monday","Tuesday","Wednesday","Thursday","Friday"],
    rows: [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:-1},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 10},{id:2},{id:-1},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:-1},{id:5}]},
            {label:"8:00-9:30",cells:[{ id: 1},{id:2},{id:3},{id:4},{id:5}]}]
}
  return (
    <><Calendar availableTerms={availableTerms} selectedTerms ={state} toggleTerm={(id)=>{
      const newSet = new Set(state.values())
      if (newSet.has(id)){
        newSet.delete(id);
            }
            else{
                newSet.add(id);
             }
      setState(newSet)
    }}/></>
  );
}

export default App;
