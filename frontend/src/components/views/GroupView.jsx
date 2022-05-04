import React, { useEffect, useState } from "react";
import http from "../../services/http";
import { Spinner } from "react-bootstrap";
import { Calendar } from "../form/calendar";
import { useParams } from "react-router-dom";

function GroupView() {
    const { id } = useParams();
    const [state, setState] = useState({
        resTable: [],
        loading: true
    });

    useEffect(() => {
        (async function () {
            const response = await http.get(`/results:${id}`);
            console.log(response.data);
            setState({
                ...state,
                resTable: response.data,
                loading: false
            });
        })();
    }, [id]);

    // return <div>GroupView</div>;
    return (
        <>
            {state.loading ? (
                <>
                    <p>Data yet not calculated</p>
                </>
            ) : (
                <Calendar
                    selectedTerms={state.resTable.selectedTerms}
                    availableTermsSet={state.resTable.availableTermsSet}
                />
            )}
        </>
    );
}

export default GroupView;
