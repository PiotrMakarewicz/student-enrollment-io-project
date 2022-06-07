import React from "react";
import "./choose.css";



function DeletionConfirmation({questionnaireId, onRefusal, onConfirmation}){

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