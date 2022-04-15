import React from "react";

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

const wrap = (element) => {
    return element.type.name && element.type.name !== "Submit" ? <div>{element}</div> : element;
};

function FormWrapper(props) {
    const clones = React.Children.map(props.children, (child) => {
        if (child.type == "title") {
            return child;
        }
        return React.cloneElement(wrap(child), {
            className: "d-flex row mb-3 w-75"
        });
    });

    return <form style={styles.formStyle}>{clones}</form>;
}

export default FormWrapper;
