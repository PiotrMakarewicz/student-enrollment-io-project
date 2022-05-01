import React, { useState } from "react";
import { Input } from "./basic";
import Select from "./basic/Select";
import CheckBox from "./basic/CheckBox";
import TextArea from "./basic/TextArea";
import Submit from "./basic/Submit";
import FileLoader from "./services/FileLoader";

/**
 * Example form
 *
 * @memberOf Form
 *
 *
 */

function ExampleForm() {
    const [state, setState] = useState({
        input: "",
        select: null,
        checkBox: false,
        textArea: ""
    });

    const onSubmit = () => {
        console.log(state);
        setState({
            input: "",
            select: "",
            checkBox: false,
            textArea: ""
        });
    };

    return (
        <form>
            <Input
                label="Enter your name"
                value={state.input}
                onChange={(v) => setState({ ...state, input: v })}
                id="example from input"
            />
            <Select
                label="Select your name"
                value={state.select}
                placeholder="whats your name"
                options={[
                    { value: 1, label: "Alice" },
                    { value: 2, label: "Bob" }
                ]}
                onChange={(v) => setState({ ...state, select: v })}
                id="example from select"
            />
            <CheckBox
                label="Agree"
                placeholder="whats your name"
                value={state.checkBox}
                onChange={(v) => setState({ ...state, checkBox: v })}
                id="example from checkbox"
            />
            <TextArea
                label="Text area test"
                placeholder="type something"
                value={state.textArea}
                onChange={(v) => setState({ ...state, textArea: v })}
                id="example from textarea"
            />
            <FileLoader />
            <Submit
                value={"Send form"}
                onSubmit={onSubmit}
            />
        </form>
    );
}

export default ExampleForm;
