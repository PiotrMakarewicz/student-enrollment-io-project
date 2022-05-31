import React, { useState } from "react";
import { QuestionnaireResults, GroupView, ImpossibilitiesAcceptance } from "../";
import { Link } from "react-router-dom";
import "./styles.css";

/**
 * @description Function returning 3-way switch for certain views
 *
 * @param {Array<String>} values
 * @param {String} selected
 *
 * @example <ViewSwitch values={["a", "b", "c"]} selected_="a"/>
 */

function ViewSwitch({ values, selected_ }) {
    const [state, setState] = useState({
        selected: selected_
    });

    const handleChange = (val) => {
        setState({ ...state, selected: val });
    };

    return (
        <>
            <Link
                className="backArrow"
                to="/questionnaires"
            >
                <button>{"\u279C"}</button>
            </Link>

            <div className="switch">
                {values.map((val, key) => {
                    return (
                        <span key={key}>
                            <input
                                type="radio"
                                id={`radio_${key}`}
                                className="radioInput"
                                onChange={() => handleChange(val)}
                                checked={val === state.selected}
                            />
                            <label
                                htmlFor={`radio_${key}`}
                                className="radioLabel"
                                onClick={() => handleChange(val)}
                            >
                                {val}
                            </label>
                        </span>
                    );
                })}
                <span
                    className="switchSelection"
                    style={{ left: `${(values.indexOf(state.selected) / 3) * 100}%` }}
                ></span>
            </div>
            <br />
            <br />
            <div className="app">
                {state.selected === "Results" && <QuestionnaireResults />}
                {state.selected === "Groups" && <GroupView />}
                {state.selected === "Impossibility" && <ImpossibilitiesAcceptance />}
                <hr />
            </div>
        </>
    );
}

export default ViewSwitch;
