import CalendarLabel from "./CalendarLabel";
import CalendarCell from "./CalendarCell";
/**
 * Create one calendar row
 * @memberof Form.Calendar
 * @param label string, label in first element
 * @param cells = list, list of objects which contains info about slots
 * @param toggleTerm function which handles slot clicking
 * @param availableTermsSet set contains available terms
 */
function CalendarRow({
  label,
  cells,
  selectedTerms,
  toggleTerm,
  availableTermsSet,
}) {
  var fields = cells.map((c, key) => (
    <CalendarCell
      key={key}
      id={c.id}
      onClick={toggleTerm}
      isAvailable={availableTermsSet.has(c.id)}
      isChosen={selectedTerms.has(c.id)}
    />
  ));
  return (
    <tr>
      <CalendarLabel label={label} />
      {fields}
    </tr>
  );
}

export default CalendarRow;
