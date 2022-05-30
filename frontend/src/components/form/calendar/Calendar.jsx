import React, { useState } from "react";
import CalendarBody from "./CalendarBody";
import CalendarHeader from "./CalendarHeader";

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
 * @param impossibleTerms set with terms that are impossibilities
 * @param termsInfo Array of terms represented in <Day> <Slot> form
 * @example
 * <Calendar selectedTerms ={availableTerms= {
        headers: ["Monday","Tuesday"],
        rows: [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:1,isAvailable: false},{id:3,isAvailable: true}}]},
    },new Set() toggleTerm ={(id)->console.log(id)}, new Set()}
 * 
 */

function Calendar({ selectedTerms, toggleTerm, availableTermsSet, impossibleTerms, termsInfo }) {
    // const [state, setState] = useState({
    //     val: 1,
    //     loading: true
    // });

    // useEffect(() => {
    //     (async function () {
    //         setState({ termsInfo: (await http.get("/terms"))["data"], loading: false });
    //     })();
    // }, []);
    return (
        <>
            <table className="calendar">
                <CalendarHeader labels={termsInfo["headers"]} />
                <CalendarBody
                    termRows={termsInfo["rows"]}
                    availableTermsSet={availableTermsSet}
                    selectedTerms={selectedTerms}
                    impossibleTerms={impossibleTerms}
                    toggleTerm={toggleTerm}
                />
            </table>
        </>
    );
}

export default Calendar;
