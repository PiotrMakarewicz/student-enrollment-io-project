import React, { useState } from "react";
import CalendarBody from "./CalendarBody";
import CalendarHeader from "./CalendarHeader";
import http from "../../../services/http";
import { Spinner } from "react-bootstrap";
import { useEffect } from "react";

/**
 * Creates calendar as a table
 * 
 * @memberof Form.Calendar
 * @param termsInfo Dictionary with terms info like data and id numbers 
 * {
 * headers : ["Monday","Tuesday"]  - available days
 * rows : [{label:"8:00-9:30",cells:[{ id: 1},{id:2}...] - available terms in next days from headers
 * }
 * @param selectedTerms set contains selected slot id
 * @param toggleTerm function which handles slot clicking
 * @param availableTermsSet set with available terms
 * @example
 * <Calendar selectedTerms ={availableTerms= {
        headers: ["Monday","Tuesday"],
        rows: [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:1,isAvailable: false},{id:3,isAvailable: true}}]},
    },new Set() toggleTerm ={(id)->console.log(id)}}
 * 
 */

function Calendar({ selectedTerms, toggleTerm, availableTermsSet }) {
    const [state, setState] = useState({
        val: 1,
        termsInfo: null,
        loading: true
    });

    useEffect(() => {
        (async function () {
            setState({ termsInfo: (await http.get("/terms"))["data"], loading: false });
        })();
    }, []);
    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <>
                    <table className="calendar">
                        <CalendarHeader labels={state.termsInfo["headers"]} />
                        <CalendarBody
                            termRows={state.termsInfo["rows"]}
                            availableTermsSet={availableTermsSet}
                            selectedTerms={selectedTerms}
                            toggleTerm={toggleTerm}
                        />
                    </table>
                </>
            )}
        </>
    );
}

export default Calendar;
