import React from "react";
import { Link } from "react-router-dom";
import {login, logout} from "../services/auth"
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
                <li>
                    <span onClick={() => login("jan.profesorski@agh.edu.pl","12345")}>Login</span>
                </li>
                <li>
                    <span onClick={() => login("wacław.f@agh.edu.pl","12345")}>Login</span>
                </li>
                <li>
                    <span onClick={logout}>Logout</span>
                </li>
            </ul>
        </header>
    );
}

export default Navbar;
