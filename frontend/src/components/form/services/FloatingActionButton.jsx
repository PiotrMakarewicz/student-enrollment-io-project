import React from "react";
import { Link } from "react-router-dom";

/**
 * @memberof form
 *
 * @type function
 *
 * @description function to read and parse delivered file
 *
 * @example <FloatingActionButton/>
 */

//  <div className="plusIcon">{"\u002B"}</div>
function FloatingActionButton() {
    return (
        <Link
            to="/addQuestionnaire"
            className="floatingActionBtn"
        >
            <div>New poll</div>
        </Link>
    );
}

export default FloatingActionButton;
