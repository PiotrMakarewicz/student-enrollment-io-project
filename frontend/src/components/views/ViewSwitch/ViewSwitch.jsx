import React, { useState } from "react";
import SimpleWrapper from "../../SimpleWrapper";
import GroupView from "../GroupView";
import QuestionnaireResults from "../QuestionnaireResults";
import http from "../../../services/http";
import "./styles.css";

function ViewSwitch() {
    const [state, setState] = useState({
        isChecked: false
    });

    const toggleHidden = () => {
        setState({ ...state, isChecked: !state.isChecked });
        http.download("/files/preferences/english/32", "preferences", "xlsx"); // temporary, just to show that downloading works :)
    };

    return (
        <>
            <SimpleWrapper>
                <input
                    type="checkbox"
                    id="switch"
                    onClick={toggleHidden}
                />
                <label
                    htmlFor="switch"
                    className="switchLabel"
                >
                    <div className="toggle"></div>
                    <div className="names">
                        <p className="results">Results</p>
                        <p className="groups">Groups</p>
                    </div>
                </label>
                <div className="app">
                    {!state.isChecked && <GroupView />}
                    {state.isChecked && <QuestionnaireResults />}
                </div>
            </SimpleWrapper>
        </>
    );
}

export default ViewSwitch;