import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import { Calendar } from "../form/calendar";
import SimpleWrapper from "../SimpleWrapper";
import http from "../../services/http";
import { useEffect } from "react";
import { Spinner } from "react-bootstrap";
import FormWrapper from "../FormWrapper";

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
        emailAdress: "",
        availableTermsSet: new Set(),
        selectedTerms: new Set(),
        loading: true
    });
    let { id } = useParams();
    var response;
    useEffect(() => {
        (async function () {
            response = await http.get("/questionnaires/" + id);
            console.log(response);
            setState({
                ...state,
                availableTermsSet: new Set(response["data"]["terms"]),
                loading: false
            });
        })();
    }, []);
    const onSubmit = () => {
        setState({
            firstName: "",
            lastName: "",
            indexNumber: "",
            emailAdress: "",
            s: state.selectedTerms,
            loading: true
        });

        const { firstName, lastName, indexNumber, emailAdress } = state;
        http.post("/vote", {
            firstName,
            lastName,
            indexNumber,
            emailAdress,
            selected_terms: Array.from(state.selectedTerms),
            questionnaire_id: id
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
                    <Spinner animation="border" />
                </>
            ) : (
                <>
                    <FormWrapper>
                        <form>
                            <Input
                                label="Enter your name"
                                value={state.firstName}
                                onChange={(v) => setState({ ...state, firstName: v })}
                                id="firstName"
                            />
                            <Input
                                label="Enter your surname"
                                value={state.lastName}
                                onChange={(v) => setState({ ...state, lastName: v })}
                                id="lastName"
                            />
                            <Input
                                label="Enter your index"
                                value={state.indexNumber}
                                onChange={(v) => setState({ ...state, indexNumber: v })}
                                id="indexNumber"
                            />
                            <Input
                                label="Enter your email"
                                value={state.emailAdress}
                                onChange={(v) => setState({ ...state, emailAdress: v })}
                                id="emailAdress"
                            />
                        </form>
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
