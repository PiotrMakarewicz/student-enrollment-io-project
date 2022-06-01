import React, { useEffect, useState } from "react";
import http from "../../../services/http";
import { Spinner } from "react-bootstrap";
import { useParams } from "react-router-dom";
import TextareaAutosize from "react-textarea-autosize";
import { DayHeader, TermHeader, GroupBody } from "../../group";
import "./impossibilities.css";
import { Submit } from "../../form/basic";

/**
 * @description function returning view with impossibilities in current questionnaire
 *
 * @example <ImpossibilitiesAcceptance/>
 *
 */

function ImpossibilitiesAcceptance() {
    const { id } = useParams();
    const [state, setState] = useState({
        data: [],
        loading: true,
        decision: []
    });
    const [inposibilitesState, setImposibilites] = useState({
        imposibillities : "" 
    });

    useEffect(() => {
        (async function () {
            const response = await http.get(`/impossibilities/${id}`);
            if (response.ok) {
                setState({
                    ...state,
                    data: response["data"],
                    loading: false,
                    decision: new Array(response["data"].votes.length).fill("default")
                });
            }
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id,inposibilitesState]);

    function button(mode, key) {
        let newDecision = [...state.decision];
        newDecision[key] = mode;
        setState({
            ...state,
            decision: newDecision
        });
    }

    async function onSubmit() {
        let decision = [];
        for (let i = 0; i < state.data.votes.length; i++) {
            if (state.decision[i] === "rejected") {
                decision.push(state.data.votes[i].id);
            }
        }
        try {
        const response = await http.post(`/impossibilities/${id}`, {
            deletedImpossibilities: decision
        });
        }
        finally{
        setImposibilites({...inposibilitesState,imposibillities : "1"})
        // window.location = window.location.href;
    }
    }

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <>
                    {state.data.votes.length === 0 ? (
                        <label>There are no impossibilities to resolve</label>
                    ) : (
                        <>
                            {state.data.votes.map((vote, key) => (
                                <div
                                    className="impossibilities"
                                    key={key}
                                >
                                    <label>
                                        {vote.student.firstName} {vote.student.lastName} at{" "}
                                        {vote.term.day} {vote.term.timeslot.startTime.slice(0, -3)}{" "}
                                        - {vote.term.timeslot.endTime.slice(0, -3)}
                                    </label>
                                    <br />
                                    <TextareaAutosize
                                        className={state.decision[key]}
                                        readOnly={true}
                                        defaultValue={vote.note}
                                        resize="none"
                                    />
                                    <br />
                                    <button
                                        type="submit"
                                        className="buttons"
                                        onClick={() => {
                                            button("accepted", key);
                                        }}
                                    >
                                        Accept
                                    </button>
                                    <button
                                        type="submit"
                                        className="buttons"
                                        onClick={() => {
                                            button("rejected", key);
                                        }}
                                    >
                                        Reject
                                    </button>
                                </div>
                            ))}
                            <Submit
                                value={"Submit"}
                                onSubmit={onSubmit}
                            ></Submit>
                        </>
                    )}
                </>
            )}
        </>
    );
}

export default ImpossibilitiesAcceptance;
