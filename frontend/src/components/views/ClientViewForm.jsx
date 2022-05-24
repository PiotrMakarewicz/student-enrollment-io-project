import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import { Calendar } from "../form/calendar";
import http from "../../services/http";
import { useEffect } from "react";
import FormWrapper from "../FormWrapper";
import Confetti from "react-confetti";

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
        firstName: "",
        lastName: "",
        indexNumber: "",
        emailAddress: "",
        availableTermsSet: new Set(),
        selectedTerms: new Set(),
        loading: true
    });
    let { hash } = useParams();
    useEffect(() => {
        (async function () {
            let data = (await http.get("/vote/" + hash))["data"];
            let student = data["student"];
            setState({
                ...state,
                availableTermsSet: new Set(data["availableTerms"]),
                selectedTerms: new Set(data["selectedTerms"]),
                firstName: student["firstName"],
                lastName: student["lastName"],
                indexNumber: student["indexNumber"],
                emailAddress: student["emailAddress"],
                loading: false
            });
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [hash]);
    const onSubmit = () => {
        const { firstName, lastName, indexNumber, emailAddress, selectedTerms } = state;
        http.post("/vote/" + hash, {
            selectedTerms: Array.from(selectedTerms)
        });

        setState({
            firstName: "",
            lastName: "",
            indexNumber: "",
            emailAddress: "",
            s: state.selectedTerms,
            loading: true
        });
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
        <>
            {state.loading ? (
                <>
                    <h1>Thanks!</h1>
                    <Confetti
                        width={1500}
                        height={700}
                    />
                </>
            ) : (
                <>
                    <FormWrapper>
                        <div className = "d-flex row mb-3 w-100">
                            <label>Name: {state.firstName}</label>
                            <label>Surname: {state.lastName}</label>
                            <label>Index: {state.indexNumber}</label>
                            <label>Email: {state.emailAddress}</label>
                        </div>
                        <Calendar
                            selectedTerms={state.selectedTerms}
                            toggleTerm={toggleTerm}
                            availableTermsSet={state.availableTermsSet}
                        />
                        <Submit
                            value={"Send form"}
                            onSubmit={onSubmit}
                        />
                    </FormWrapper>
                </>
            )}
        </>
    );
}

export default ClientViewForm;
