import React, { useState } from "react";
import DatePicker from "react-widgets/DatePicker";
import TimeInput from "react-widgets/TimeInput";
import "react-widgets/styles.css";

import { CheckBox, Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import FormWrapper from "../FormWrapper";
import { Calendar } from "../form/calendar";
import http from "../../services/http";

/**
 *
 * Returns Lecturer form
 *
 * @memberof Form
 *
 */

function LecturerForm() {
    const [state, setState] = useState({
        name_input: "",
        fullname_input: "",
        date_input: new Date(),
        time_input: new Date(new Date().setHours(0, 0, 0, 0)),
        selected_terms: new Set()
    });
    const onSubmit = async () => {
        const { date_input, time_input, name_input, selected_terms } = state;
        const response = await http.post("/questionnaires", {
            expirationDate: new Date(
                date_input.getFullYear(),
                date_input.getMonth(),
                date_input.getDate(),
                time_input.getHours(),
                time_input.getMinutes()
            ),
            label: name_input,
            availableTerms: Array.from(selected_terms)
        });

        if (response.ok) {
            setState({
                name_input: "",
                fullname_input: "",
                date_input: new Date(),
                time_input: new Date(new Date().setHours(0, 0, 0, 0)),
                selected_terms: new Set()
            });
        }
    };

    const toggleTerm = (id) => {
        const { selected_terms } = state;

        if (selected_terms.has(id)) {
            selected_terms.delete(id);
        } else {
            selected_terms.add(id);
        }
        setState({ ...state });
    };

    return (
        <>
            <FormWrapper>
                <title className="display-6 pb-5 pt-2">New poll</title>
                <Input
                    type={"text"}
                    label="Subject Name"
                    value={state.name_input}
                    onChange={(v) => setState({ ...state, name_input: v })}
                    id={"name_input"}
                />

                <Input
                    type={"text"}
                    label="Lecturer Full Name"
                    value={state.fullname_input}
                    onChange={(v) => setState({ ...state, fullname_input: v })}
                    id={"fullname_input"}
                    placeholder={""}
                />

                <div className="row">
                    <div className="col-xs-12 col-sm-6">
                        <label htmlFor="date_picker">Expiry Date</label>
                        <DatePicker
                            defaultValue={new Date()}
                            className="mb-1"
                            value={state.date_input}
                            id="date_picker"
                            onChange={(v) => setState({ ...state, date_input: v })}
                        />
                    </div>
                    <div className="col-xs-12 col-sm-6">
                        <label
                            className="w-100"
                            htmlFor="time_input"
                        >
                            Expiry Time
                        </label>
                        <TimeInput
                            defaultValue={null}
                            className="mb-1"
                            style={{ width: "min(80%, 30vw)" }}
                            value={state.time_input}
                            id="time_input"
                            onChange={(v) => setState({ ...state, time_input: v })}
                        />
                    </div>
                </div>

                <CheckBox
                    label="Agree"
                    placeholder="whats your name"
                    value={state.checkBox}
                    onChange={(v) => setState({ ...state, checkBox: v })}
                    id="example from checkbox"
                />

                <Calendar
                    selectedTerms={state.selected_terms}
                    toggleTerm={toggleTerm}
                    availableTermsSet="All"
                />

                <Submit
                    value={"Continue"}
                    onSubmit={onSubmit}
                />
            </FormWrapper>
        </>
    );
}

export default LecturerForm;
