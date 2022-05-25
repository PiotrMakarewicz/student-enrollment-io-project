import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import { Calendar } from "../form/calendar";
import http from "../../services/http";
import { useEffect } from "react";
import FormWrapper from "../FormWrapper";
import Confetti from "react-confetti";
import { toast } from "react-toastify";

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
        name: "",
        loading: true
    });
    let { id } = useParams();
    useEffect(() => {
        (async function () {
            const reqResult = (await http.get(`/questionnaires/${id}`))["data"];

            setState({
                ...state,
                availableTermsSet: new Set(reqResult.terms),
                loading: false,
                name: reqResult.detail.label
            });
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id]);
    const onSubmit = () => {
        const { firstName, lastName, indexNumber, emailAdress, selectedTerms } = state;
        http.post("/vote", {
            firstName,
            lastName,
            indexNumber,
            emailAdress,
            selectedTerms: Array.from(selectedTerms),
            questionnaireId: id
        });

        toast.success("Questionnaire sent", {
            position: "top-center",
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined
        });
        setState({
            firstName: "",
            lastName: "",
            indexNumber: "",
            emailAdress: "",
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
                        <form>
                            <h2>{state.name}</h2>
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
