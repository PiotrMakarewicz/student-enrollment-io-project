import CalendarRow from "./CalendarRow";

/**
 * Create calendar body without headers
 * @memberof Form.Calendar
 * @param termRows list of objects which contains info about specific term
 * [{label:"8:00-9:30",cells:[{ id: 1,isAvailable: true},{id:-1,isAvailable: false}...]
 * @param selectedTerms set contains selected terms
 * @param toggleTerm function which handles slot clicking
 * @param availableTermsSet set contains available terms
 * @example  <CalendarBody termRows={availableTerms.rows} selectedTerms={selectedTerms} toggleTerm={toggleTerm}/>
 *
 */
function CalendarBody({
  termRows,
  selectedTerms,
  toggleTerm,
  availableTermsSet,
}) {
  var calendarRows = termRows.map((r, key) => (
    <CalendarRow
      key={key}
      label={r.label}
      cells={r.cells}
      availableTermsSet={availableTermsSet}
      selectedTerms={selectedTerms}
      toggleTerm={toggleTerm}
    />
  ));

  return <tbody>{calendarRows}</tbody>;
}

export default CalendarBody;
