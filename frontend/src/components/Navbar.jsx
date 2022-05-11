import React from "react";
import { Link } from "react-router-dom";

/**
 *
 * Returns Navbar component for Lecturer page
 */

function Navbar() {
    return (
        <header className="App-header px-4">
            <Link to="/">Home</Link>
            <ul>
                <li data-active="false">
                    <Link to="/addQuestionnaire">New poll</Link>
                </li>
                <li data-active="false">
                    <Link to="/questionnaires">Your polls</Link>
                </li>
            </ul>
        </header>
    );
}

export default Navbar;
