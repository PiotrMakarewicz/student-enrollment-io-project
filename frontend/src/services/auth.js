import http from "./http";
import { toast } from "react-toastify";
import jwtDecode from "jwt-decode";

if(localStorage.getItem("token"))
    http.setToken(localStorage.getItem("token"));

export const  login = async (email, password) =>{
    const result = await http.post('/auth',{email,password})
    console.log(result);
    if(!result.ok){
        toast.error('Login failed', result.data);
        return result;
    }

    localStorage.setItem("token", result.data["token"]);
    toast.success('Login Succes!!!');
    http.setToken(result.data.token);
    return result;
}

export const getLoggedUser = () => {
    if(!localStorage.getItem("token")) return null;
    return jwtDecode(localStorage.getItem("token"));
}

export const logout = () => {
    console.log("log out");
    http.setToken(null)
    localStorage.removeItem("token");
}