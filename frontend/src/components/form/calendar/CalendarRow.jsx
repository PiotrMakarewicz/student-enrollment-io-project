import CalendarLabel from "./CalendarLabel";
import CalendarCell from "./CalendarCell";
/**
 * Create one calendar row
 * @memberof Form.Calendar
 * @param label string, label in first element
 * @param cells = list, list of objects which contains info about slots
 * @param toggleTerm function which handles slot clicking
 * @param availableTermsSet set contains available terms
 * @param impossibleTerms set with terms that are impossibilities
 */
function CalendarRow({
    label,
    cells,
    selectedTerms,
    toggleTerm,
    availableTermsSet,
    impossibleTerms
}) {
    var fields = cells.map((c, key) => (
        <CalendarCell
            key={key}
            id={c}
            onClick={toggleTerm}
            isAvailable={availableTermsSet === "All" || availableTermsSet.has(c)}
            isChosen={selectedTerms.has(c)}
            isImpossible={c in impossibleTerms}
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
