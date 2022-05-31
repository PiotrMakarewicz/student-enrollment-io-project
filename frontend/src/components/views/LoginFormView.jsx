import React, { useState } from 'react'
import FormWrapper from '../FormWrapper';
import { Input, Submit } from '../form/basic'
import {login} from '../../services/auth'


function LoginFormView() {
    console.log("render")


    const [state,setState] = useState({
        email:"",
        emailError:"",
        loginError:"",
        password:"",
    })

    const setAndValidateEmail = (newEmail) => {
        let emailError = null;

        let regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!newEmail.match(regexEmail)) 
            emailError = "Email is no valid";
        else
            emailError = null
        console.log(newEmail,{ ...state ,email:newEmail, emailError})
        setState({ ...state ,email:newEmail, emailError});
    }


    const setPassword = (newPassword) =>{
        setState({...state, password:newPassword})
    }

    const tryLogin = async () => {
        const {email, password} = state;
        const response = await login(email, password);
        if(!response.ok) setState({...state, loginError: response.data.message});
        else      window.location =  "/";
    }

    return (
        <FormWrapper>
            <Input 
                label="Email" 
                id="lf_email" 
                placeholder="enter email" 
                value={state.email} 
                onChange={setAndValidateEmail} 
                error={state.emailError}
            />
            <Input 
                label="Password" 
                id="lf_password" 
                placeholder="enter password" 
                type="password" 
                value={state.password} 
                onChange={setPassword} 
            />
            <Submit value="Login" onSubmit={tryLogin} error={state.loginError}/>
        </FormWrapper>
    );
}

export default LoginFormView;