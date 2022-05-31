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
import { Spinner } from "react-bootstrap";

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
        impossibleTerms: new Map(),
        termsInfo: null,
        loadingState: 0,
        subjectName: ""
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
                // eslint-disable-next-line no-new-object
                impossibleTerms: new Object(data["impossibleTerms"]),
                termsInfo: (await http.get("/terms"))["data"],
                loadingState: 1,
                subjectName: data["label"],
                firstName: student["firstName"],
                lastName: student["lastName"],
                indexNumber: student["indexNumber"],
                emailAddress: student["emailAddress"]
            });
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [hash]);
    const onSubmit = () => {
        const { firstName, lastName, indexNumber, emailAddress, selectedTerms, impossibleTerms } =
            state;
        http.post("/vote/" + hash, {
            firstName,
            lastName,
            indexNumber,
            emailAddress,
            selectedTerms: Array.from(selectedTerms),
            impossibleTerms: impossibleTerms
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
            emailAddress: "",
            s: state.selectedTerms,
            loadingState: 2
        });
    };

    var toggleTerm = (id) => {
        const { selectedTerms, impossibleTerms } = state;
        if (!selectedTerms.has(id) && !(id in impossibleTerms)) {
            selectedTerms.add(id);
        } else if (selectedTerms.has(id) && !(id in impossibleTerms)) {
            selectedTerms.delete(id);
            impossibleTerms[id] = "";
        } else if (!selectedTerms.has(id) && id in impossibleTerms) {
            delete impossibleTerms[id];
        }
        setState({ ...state });
    };
    if (state.loadingState === 0) {
        return (
            <>
                <Spinner animation="border" />
            </>
        );
    } else if (state.loadingState === 1) {
        return (
            <>
                <FormWrapper>
                    <div className="d-flex row mb-3 w-100">
                        <h2>{state.subjectName}</h2>
                        <label>Name: {state.firstName}</label>
                        <label>Surname: {state.lastName}</label>
                        <label>Index: {state.indexNumber}</label>
                        <label>Email: {state.emailAddress}</label>
                    </div>

                    <Calendar
                        selectedTerms={state.selectedTerms}
                        toggleTerm={toggleTerm}
                        availableTermsSet={state.availableTermsSet}
                        impossibleTerms={state.impossibleTerms}
                        termsInfo={state.termsInfo}
                    />
                    {Object.keys(state.impossibleTerms).map((t, key) => (
                        <Input
                            label={
                                "Explain your impossibility for " +
                                [[""], ...state.termsInfo["headers"]][Math.floor(t / 10) + 1] +
                                " " +
                                state.termsInfo["rows"][(t % 10) - 1].label
                            }
                            value={state.impossibleTerms[t]}
                            onChange={(v) => {
                                const { impossibleTerms } = state;
                                impossibleTerms[t] = v;
                                setState({ ...state });
                            }}
                            id={"impossibility" + t}
                            key={key}
                        />
                    ))}
                    <Submit
                        value={"Send form"}
                        onSubmit={onSubmit}
                    />
                </FormWrapper>
            </>
        );
    } else
        return (
            <>
                <h1>Thanks!</h1>
                <Confetti
                    width={1920}
                    height={900}
                />
            </>
        );
}

export default ClientViewForm;
