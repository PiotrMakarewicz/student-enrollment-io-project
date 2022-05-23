import React, { useState } from "react";
import SimpleWrapper from "../../SimpleWrapper";
import { QuestionnaireResults, GroupView } from "../";
import { Link } from "react-router-dom";
import "./styles.css";

function ViewSwitch() {
    const [state, setState] = useState({
        isChecked: false
    });

    const toggleHidden = () => {
        setState({ ...state, isChecked: !state.isChecked });
        //  http.download("/files/preferences/english/32", "preferences", "xlsx"); // temporary, just to show that downloading works :)
    };

    return (
        <>
            <Link
                className="backArrow"
                to="/questionnaires"
            >
                <button>{"\u279C"}</button>
            </Link>

            <input
                type="checkbox"
                id="switch"
                onClick={toggleHidden}
                hidden
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
                {!state.isChecked && <QuestionnaireResults />}
                {state.isChecked && <GroupView />}
                <hr />
            </div>
        </>
    );
}

export default ViewSwitch;
