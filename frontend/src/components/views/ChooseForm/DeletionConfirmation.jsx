import React from "react";
import { Submit } from "../../form/basic";
import "./choose.css";

function DeletionConfirmation({ questionnaireId, onRefusal, onConfirmation }) {
    return (
        <div className="floatingConfirmation">
            Do you want to delete questionnaire {questionnaireId.name}?
            <div>
                <Submit
                    value={"Yes"}
                    onSubmit={onConfirmation}
                    cl="btn btn-light"
                />
                <Submit
                    value={"No"}
                    onSubmit={onRefusal}
                    cl="btn btn-danger"
                />
            </div>
        </div>
    );
}

export default DeletionConfirmation;
