import React, { useState, useEffect } from "react";
import http from "../../../services/http";
import Button from "../../basic/Button";
import "./choose.css";



async function onClick(questionnaireId){
    http.post()
}



function DeletionConfirmation({questionnaireId, onRefusal, onConfirmation}){
    const [state, setState] = useState({
        loading:false
    });

    return (
        <div className="floatingConfirmation">
            Do you want to delete questionnaire {questionnaireId.name}?
            <div>
                <button 
                    className="confirmation"
                    onClick={onConfirmation}
                >YES</button>
                <button 
                    className="confirmation"
                    onClick={onRefusal}
                >NO</button>
            </div>
            
        </div>
    );
}


export default DeletionConfirmation;