import React,{useState} from 'react'
import { Input } from "../basic";
import Submit from '../basic/Submit'
import {Calendar} from '../calendar'
import http from '../../../services/http';
import { useEffect } from "react";

/**
 * View form for client
 * 
 * @param formId @type int @description questionnaire ID  
 * @example
 * <ClientViewForm formId={1}>
 * 
 */

function ClientViewForm({formId}) {
    const [state, setState] = useState({
        name:"",
        surname:"",
        index:"",
        email:"",
        availableTermsSet:new Set(),
        selectedTerms:new Set()
      })

      var response;
      useEffect(()=> {
          (async function(){
            response = await http.get("/questionnaires/"+formId);
            setState({...state,availableTermsSet:new Set(response["data"]["terms"])});
          })()
        },[])
    const onSubmit = () => {
    console.log(state)
    setState( {
        name:"",
        surname:"",
        index:"",
        email:"",
        s: state.selectedTerms
    })

    }

    var toggleTerm=(id)=>{
        const {selectedTerms}=state;
        if (selectedTerms.has(id)){
            selectedTerms.delete(id);
              }
              else{
                selectedTerms.add(id);
               }
        setState({...state})
      }
    return (
        <form>
            <Input
                label="Enter your name" 
                value={state.name} 
                onChange={(v) => setState({...state,name:v})}
                id="name"
            />
            <Input
                label="Enter your surname" 
                value={state.surname} 
                onChange={(v) => setState({...state,surname:v})}
                id="surname"
            />
            <Input
                label="Enter your index" 
                value={state.index} 
                onChange={(v) => setState({...state,index:v})}
                id="index"
            />
            <Input
                label="Enter your email" 
                value={state.email} 
                onChange={(v) => setState({...state,email:v})}
                id="email"
            />
            <Calendar selectedTerms={state.selectedTerms} toggleTerm={toggleTerm} availableTermsSet={state.availableTermsSet}/>
            <Submit 
                value={"Send form"} 
                onSubmit={onSubmit} 
            />
        </form>

      );
}

export default ClientViewForm;