import './App.css';

import { useState } from 'react';
import ClientViewForm from './components/form/clientForm/ClientViewForm';

function App() {
  const [state,setState] = useState(new Set())
  var availableTerms= {
    headers: ["Monday","Tuesday","Wednesday","Thursday","Friday"],
    rows: [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:2,isAvailable:false},{id: 3,isAvailable: true},{id: 4,isAvailable: true},{id: 5,isAvailable: false}]},
            {label:"9:35-11:05",cells:[{ id: 6,isAvailable: true},{id: 7,isAvailable: true},{id: 8,isAvailable: true},{id: 9,isAvailable: false},{id: 10,isAvailable: true}]},
            {label:"11:15-12:45",cells:[{ id: 11,isAvailable: false},{id: 12,isAvailable: true},{id: 13,isAvailable: false},{id: 14,isAvailable: true},{id: 15,isAvailable: true}]},
            {label:"12:50-14:20",cells:[{ id: 16,isAvailable: true},{id: 17,isAvailable: false},{id: 18,isAvailable: true},{id: 19,isAvailable: true},{id: 20,isAvailable: false}]},
            {label:"14:40-16:10",cells:[{ id: 21,isAvailable:false},{id: 22,isAvailable: false},{id: 23,isAvailable: true},{id: 24,isAvailable: false},{id: 25,isAvailable: true}]},
            {label:"16:15-17:45",cells:[{ id: 26,isAvailable: false},{id: 27,isAvailable: true},{id: 28,isAvailable: true},{id: 29,isAvailable: true},{id: 30,isAvailable: true}]},
            {label:"17:50-19:20",cells:[{ id: 31,isAvailable: true},{id: 32,isAvailable: true},{id: 33,isAvailable: false},{id: 34,isAvailable: true},{id: 35,isAvailable: false}]}]
}
  return (
    <><ClientViewForm availableTerms={availableTerms} selectedTerms ={state} toggleTerm={(id)=>{
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
