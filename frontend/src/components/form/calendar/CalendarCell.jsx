/**
 *Creates CalendarCell
 * @memberof Form.Calendar
 *
 * @param id int,slot number
 * @param label string,displayed label
 * @param onClick function to handle cliclikng on the slot
 * @param isAvailable bool, true if term is available
 * @param isChosen bool, true if student chose this term
 * @param isLabel bool, true if Cell is CalendarLabel
 * @param isImpossible bool, true if Cell is marked as impossible
 *
 * @example <CalendarCell id = {1} onClick= {()=>console.log(id), isAvailabe ={true}, isChosen ={false} isLabel={false}}
 */

function CalendarCell({ id, label, onClick, isAvailable, isChosen, isLabel, isImpossible }) {
    let classes = "calendar-cell-not-available";
    if (isAvailable) classes = "calendar-cell-available";
    if (isChosen) classes = "calendar-cell-chosen";
    if (isLabel) classes = "calendar-cell-label";
    if (isImpossible) classes = "calendar-cell-impossibility";

    return (
        <td
            className={"calendar-cell " + classes}
            onClick={isAvailable ? () => onClick(id) : undefined}
            onKeyDown={undefined}
            role="presentation"
        >
            {label}
        </td>
    );
}

export default CalendarCell;
