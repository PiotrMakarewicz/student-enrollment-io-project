import React from "react";
import { Link } from "react-router-dom";

/**
 *
 * Returns Navbar component for Lecturer page
 */

function Navbar() {
    // const links = document.querySelectorAll("[data-active]");
    // links.forEach((link) => {
    //     link.addEventListener("click", () => {
    //         document.querySelector('[data-active="true"]').dataset.active = false;
    //         link.dataset.active = "true";
    //     });
    // });

    return (
        <header className="App-header px-4">
            <Link to="/">Home</Link>
            <ul>
                <li data-active="false">
                    <Link to="/new-form">New poll</Link>
                </li>
                <li data-active="false">
                    <Link to="/my-forms">Your polls</Link>
                </li>
                <li data-active="false">
                    <Link to="/settings">Settings</Link>
                </li>
            </ul>
        </header>
    );
}

export default Navbar;
