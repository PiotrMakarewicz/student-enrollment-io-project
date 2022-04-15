import React from "react";

/**
 * Wrapper component for adding styles to forms
 * To add title/header to form use <title></title> tag so it is not affected by the wrapper.
 *
 *
 * @param {Object} props   FormWrapper should contain other components as its props.children
 */

const styles = {
    formStyle: {
        padding: "1.25em 1em",
        margin: ".75em .25em",
        height: "fit-content",
        backgroundColor: "var(--bs-gray-200)",
        boxShadow: "0 8px 16px 6px #ccc",
        borderRadius: ".25em",
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
    }
};



function SimpleWrapper(props) {

    return (<div style={styles.formStyle} className="container-lg">
        {props.children}
    </div>);
}

export default SimpleWrapper;
