import React from "react";
import "./styles.css";

function ViewSwitch() {
    const onClick = (e) => {
        e.preventDefault();
        return;
    };

    return (
        <>
            <input
                type="checkbox"
                id="switch"
                onClick={onClick}
            />
            <label htmlFor="switch">
                <div className="toggle"></div>
                <div className="names">
                    <p className="results">Results</p>
                    <p className="groups">Groups</p>
                </div>
            </label>
        </>
    );
}

export default ViewSwitch;
