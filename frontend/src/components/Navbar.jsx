import React from "react";
import { Link } from "react-router-dom";
import { getLoggedUser } from "../services/auth";
/**
 *
 * Returns Navbar component for Lecturer page
 */

function Navbar() {
    const user = getLoggedUser();
    return (
        <header className="App-header">
            <Link to="/aboutUs">About us</Link>
            <ul>
                {user != null ? (
                    <li>
                        <Link to="/addQuestionnaire">New poll</Link>
                    </li>
                ) : (
                    <></>
                )}
                {user != null ? (
                    <li>
                        <Link to="/questionnaires">Your polls</Link>
                    </li>
                ) : (
                    <></>
                )}
                {user != null ? (
                    <li>
                        <Link to="/logout">Logout</Link>
                    </li>
                ) : (
                    <li>
                        <Link to="/login">Login</Link>
                    </li>
                )}
            </ul>
        </header>
    );
}

export default Navbar;
