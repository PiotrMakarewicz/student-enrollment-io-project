import React from "react";

function Button({ onClick, value, disable }) {
    return (
        <button
            type="submit"
            className="btn btn-primary"
            style={{ display: "inline" }}
            onClick={onClick}
            disabled={disable}
        >
            {value}
        </button>
    );
}

export default Button;
