import { useState } from "react";
import CalendarCell from "./CalendarCell";
import CalendarHeader from "./CalendarHeader";
import CalendarLabel from "./CalendarLabel";

/**
 * Creates calendar as a table
 * 
 * @memberof ClientViewForm
 * @param availableTermins Dictionary with available terms 
 * {
 * headers : ["Monday","Tuesday"]  - available days
 * rows : [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:-1,isAvailable: false}...] - available terms in next days from headers
 * }
 * @param selectedTerms set contains selected slot id
 * @param toggleTerm function which handles slot clicking
 * 
 * @example
 * <Calendar selectedTerms ={availableTerms= {
        headers: ["Monday","Tuesday"],
        rows: [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:1,isAvailable: false},{id:3,isAvailable: true}}]},
    },new Set() toggleTerm ={(id)->console.log(id)}}
 * 
 */

function Calendar({availableTerms,selectedTerms,toggleTerm}) { 
 
    var calendarRows = availableTerms.rows.map((r, key)=><tr key={key}><CalendarLabel label={r.label} />
    {r.cells.map((c,key)=><CalendarCell key={key} id={c.id} onClick={toggleTerm} isAvailable={c.isAvailable} isChosen={selectedTerms.has(c.id)}/>)}</tr>);
    return <table><CalendarHeader labels={availableTerms.headers}/><tbody>{calendarRows}</tbody></table>;
}

export default Calendar;