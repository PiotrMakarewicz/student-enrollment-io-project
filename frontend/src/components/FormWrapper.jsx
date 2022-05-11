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
        padding: "1.25em 2.5em",
        margin: ".75em .25em",
        height: "fit-content",
        backgroundColor: "var(--bs-gray-700)",
        boxShadow: "0 8px 16px 4px #6c757d",
        borderRadius: ".25em",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "70vw",
        color: "white"
    }
};

const wrap = (element) => {
    return element.type.name && element.type.name !== "Submit" ? <div>{element}</div> : element;
};

function FormWrapper(props) {
    const clones = React.Children.map(props.children, (child) => {
        if (child.type === "title") {
            return child;
        }
        return React.cloneElement(wrap(child), {
            className: "d-flex row mb-3 w-100"
        });
    });

    return <form style={styles.formStyle}>{clones}</form>;
}

export default FormWrapper;
