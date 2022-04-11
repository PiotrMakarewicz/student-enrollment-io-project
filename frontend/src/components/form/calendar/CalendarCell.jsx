/**
 *Creates CalendarCell
 * @memberof CalendarLabel and Calendar
 * 
 * @param id int,slot number
 * @param label string,displayed label
 * @param onClick function to handle cliclikng on the slot
 * @param isAvailable bool, true if term is available 
 * @param isChosen bool, true if student chose this term
 * @param isLabel bool, true if Cell is CalendarLabel
 * 
 * @example <CalendarCell id = {1} onClick= {()=>console.log(id), isAvailabe ={true}, isChosen ={false} isLabel={false}}
 */


function CalendarCell({ id,label,onClick,isAvailable,isChosen,isLabel}) {
    let color = "DimGray";
    if (isAvailable) color = "LightGrey";
    if (isChosen) color ="LightGreen";
    if(isLabel) color="orange";

    const cellStyle={ 
        padding: "0.5em 0",
        backgroundColor: color,
        border: "0.5px solid"
    }
    
    return (<td className="calendar-cell" style={cellStyle} onClick ={isAvailable? ()=>onClick(id): undefined }>{label}</td>);
}

export default CalendarCell;