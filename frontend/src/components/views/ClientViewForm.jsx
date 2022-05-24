import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import { Calendar } from "../form/calendar";
import http from "../../services/http";
import { useEffect } from "react";
import FormWrapper from "../FormWrapper";
import Confetti from "react-confetti";
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
        emailAdress: "",
        availableTermsSet: new Set(),
        selectedTerms: new Set(),
        impossibleTerms: {},
        termsInfo: null,
        loadingState: 0
    });
    let { hash } = useParams();
    useEffect(() => {
        (async function () {
            let data = (await http.get("/vote/" + hash))["data"];
            setState({
                ...state,
                availableTermsSet: new Set(data["availableTerms"]),
                selectedTerms: new Set(data["selectedTerms"]),
                termsInfo: (await http.get("/terms"))["data"],
                loadingState: 1
            });
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [hash]);
    const onSubmit = () => {
        const { firstName, lastName, indexNumber, emailAdress, selectedTerms, impossibleTerms } =
            state;
        http.post("/vote/" + hash, {
            firstName,
            lastName,
            indexNumber,
            emailAdress,
            selectedTerms: Array.from(selectedTerms),
            impossibleTerms: Array.from(impossibleTerms)
        });

        setState({
            firstName: "",
            lastName: "",
            indexNumber: "",
            emailAdress: "",
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
    if(state.loadingState === 0){
        return(
            <>
                    <Spinner animation="border" />
            </>
        ); 
    }
    else if (state.loadingState === 1){
        return(
            <>
                    <FormWrapper>
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
    }
    else return(
        <>
                <h1>Thanks!</h1>
                <Confetti
                    width={1500}
                    height={700}
                />
            </>
    );
    // return (
    //     <>
    //         {
    //         state.loadingState ? (
    //             <>
    //                 <h1>Thanks!</h1>
    //                 <Confetti
    //                     width={1500}
    //                     height={700}
    //                 />
    //             </>
    //         ) : (
    //             <>
    //                 <FormWrapper>
    //                         <Input
    //                             label="Enter your name"
    //                             value={state.firstName}
    //                             onChange={(v) => setState({ ...state, firstName: v })}
    //                             id="firstName"
    //                         />
    //                         <Input
    //                             label="Enter your surname"
    //                             value={state.lastName}
    //                             onChange={(v) => setState({ ...state, lastName: v })}
    //                             id="lastName"
    //                         />
    //                         <Input
    //                             label="Enter your index"
    //                             value={state.indexNumber}
    //                             onChange={(v) => setState({ ...state, indexNumber: v })}
    //                             id="indexNumber"
    //                         />
    //                         <Input
    //                             label="Enter your email"
    //                             value={state.emailAdress}
    //                             onChange={(v) => setState({ ...state, emailAdress: v })}
    //                             id="emailAdress"
    //                         />
    //                     <Calendar
    //                         selectedTerms={state.selectedTerms}
    //                         toggleTerm={toggleTerm}
    //                         availableTermsSet={state.availableTermsSet}
    //                         impossibleTerms={state.impossibleTerms}
    //                         termsInfo={state.termsInfo}
    //                     />
    //                     {Object.keys(state.impossibleTerms).map((t, index) => (
    //                         <Input
    //                             label={
    //                                 "Explain your impossibility for " +
    //                                 [[""], ...state.termsInfo["headers"]][Math.floor(t / 10) + 1] +
    //                                 " " +
    //                                 state.termsInfo["rows"][(t % 10) - 1].label
    //                             }
    //                             value={state.impossibleTerms[t]}
    //                             onChange={(v) => {
    //                                 const { impossibleTerms } = state;
    //                                 impossibleTerms[t] = v;
    //                                 setState({ ...state });
    //                             }}
    //                             id={"impossibility" + t}
    //                         />
    //                     ))}
    //                     <Submit
    //                         value={"Send form"}
    //                         onSubmit={onSubmit}
    //                     />
    //                 </FormWrapper>
    //             </>
    //         )}
    //     </>
    // );
}

export default ClientViewForm;
