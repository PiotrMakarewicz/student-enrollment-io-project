import React, { useEffect, useState } from "react";
import http from "../../services/http";
import { Spinner } from "react-bootstrap";
import { Calendar } from "../form/calendar";
import { useParams } from "react-router-dom";

function GroupView() {
    const { id } = useParams();
    const [state, setState] = useState({
        availableTermsSet: new Set(),
        selectedTerms: new Set(),
        loading: true
    });

    useEffect(() => {
        (async function () {
            const response = await http.get(`/questionnaires/${id}`);
            console.log(response.data);
            setState({
                ...state,
                availableTermsSet: new Set(response["data"]["terms"]),
                loading: false
            });
        })();
    }, [id]);

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <div className="groupView">
                    <Calendar
                        selectedTerms={state.selectedTerms}
                        toggleTerm={() => {
                            console.log("hej");
                        }}
                        availableTermsSet={state.availableTermsSet}
                    />
                </div>
            )}
        </>
    );
}

export default GroupView;
