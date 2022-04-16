import React, { useState } from "react";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import { Calendar } from "../form/calendar";
import SimpleWrapper from "../SimpleWrapper";

/**
 * View form for client
 *
 * @param formId @type int @description questionnaire ID
 * @example
 * <ClientViewForm formId={1}>
 *
 */

function ClientViewForm() {
    const [state, setState] = useState({
        name: "",
        surname: "",
        index: "",
        email: "",
        availableTermsSet: new Set([1, 6, 9, 12, 18, 24, 28]),
        selectedTerms: new Set()
    });
    const onSubmit = () => {
        console.log(state);
        setState({
            name: "",
            surname: "",
            index: "",
            email: "",
            s: state.selectedTerms
        });
    };
    var termsInfo = {
        headers: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"],
        rows: [
            {
                label: "8:00-9:30",
                cells: [
                    { id: 1, isAvailable: true },
                    { id: 2, isAvailable: false },
                    { id: 3, isAvailable: true },
                    { id: 4, isAvailable: true },
                    { id: 5, isAvailable: false }
                ]
            },
            {
                label: "9:35-11:05",
                cells: [
                    { id: 6, isAvailable: true },
                    { id: 7, isAvailable: true },
                    { id: 8, isAvailable: true },
                    { id: 9, isAvailable: false },
                    { id: 10, isAvailable: true }
                ]
            },
            {
                label: "11:15-12:45",
                cells: [
                    { id: 11, isAvailable: false },
                    { id: 12, isAvailable: true },
                    { id: 13, isAvailable: false },
                    { id: 14, isAvailable: true },
                    { id: 15, isAvailable: true }
                ]
            },
            {
                label: "12:50-14:20",
                cells: [
                    { id: 16, isAvailable: true },
                    { id: 17, isAvailable: false },
                    { id: 18, isAvailable: true },
                    { id: 19, isAvailable: true },
                    { id: 20, isAvailable: false }
                ]
            },
            {
                label: "14:40-16:10",
                cells: [
                    { id: 21, isAvailable: false },
                    { id: 22, isAvailable: false },
                    { id: 23, isAvailable: true },
                    { id: 24, isAvailable: false },
                    { id: 25, isAvailable: true }
                ]
            },
            {
                label: "16:15-17:45",
                cells: [
                    { id: 26, isAvailable: false },
                    { id: 27, isAvailable: true },
                    { id: 28, isAvailable: true },
                    { id: 29, isAvailable: true },
                    { id: 30, isAvailable: true }
                ]
            },
            {
                label: "17:50-19:20",
                cells: [
                    { id: 31, isAvailable: true },
                    { id: 32, isAvailable: true },
                    { id: 33, isAvailable: false },
                    { id: 34, isAvailable: true },
                    { id: 35, isAvailable: false }
                ]
            }
        ]
    };

    var toggleTerm = (id) => {
        const { selectedTerms } = state;
        if (selectedTerms.has(id)) {
            selectedTerms.delete(id);
        } else {
            selectedTerms.add(id);
        }
        setState({ ...state });
    };
    return (
        <SimpleWrapper>
            <form>
                <Input
                    label="Enter your name"
                    value={state.name}
                    onChange={(v) => setState({ ...state, name: v })}
                    id="name"
                />
                <Input
                    label="Enter your surname"
                    value={state.surname}
                    onChange={(v) => setState({ ...state, surname: v })}
                    id="surname"
                />
                <Input
                    label="Enter your index"
                    value={state.index}
                    onChange={(v) => setState({ ...state, index: v })}
                    id="index"
                />
                <Input
                    label="Enter your email"
                    value={state.email}
                    onChange={(v) => setState({ ...state, email: v })}
                    id="email"
                />
                <Calendar
                    termsInfo={termsInfo}
                    selectedTerms={state.selectedTerms}
                    toggleTerm={toggleTerm}
                    availableTermsSet={state.availableTermsSet}
                />
                <Submit
                    value={"Send form"}
                    onSubmit={onSubmit}
                />
            </form>
        </SimpleWrapper>
    );
}

export default ClientViewForm;
