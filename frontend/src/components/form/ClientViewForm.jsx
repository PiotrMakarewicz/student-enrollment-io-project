import React,{useState} from 'react'
import { Input } from "./basic";
import Submit from './basic/Submit'



function ClientViewForm() {
    
    const [state, setState] = useState({
        name:"",
        surname:"",
        index:"",
        email:"",
      })
    const onSubmit = () => {
    console.log(state)
    setState( {
        name:"",
        surname:"",
        index:"",
        email:"",
    })
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
            <Submit 
                value={"Send form"} 
                onSubmit={onSubmit} 
            />
        </form>
      );
}

export default ClientViewForm;