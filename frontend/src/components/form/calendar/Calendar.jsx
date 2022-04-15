import CalendarBody from "./CalendarBody";
import CalendarHeader from "./CalendarHeader";

/**
 * Creates calendar as a table
 * 
 * @memberof ClientViewForm
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

function Calendar({ termsInfo, selectedTerms, toggleTerm, availableTermsSet }) {
  return (
    <table className="calendar">
      <CalendarHeader labels={termsInfo.headers} />
      <CalendarBody
        termRows={termsInfo.rows}
        availableTermsSet={availableTermsSet}
        selectedTerms={selectedTerms}
        toggleTerm={toggleTerm}
      />
    </table>
  );
}

export default Calendar;
