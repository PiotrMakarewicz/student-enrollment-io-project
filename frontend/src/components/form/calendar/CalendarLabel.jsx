import CalendarCell from "./CalendarCell";
/**
 * Creates labelField in Calendar
 *
 * @memberOf CalendarHeader and Calendar
 * @param label
 *
 * @example <Calendarlabel label ={"Monday"}
 */
function CalendarLabel({ label }) {
  return <CalendarCell label={label} isAvailable={false} isLabel={true} />;
}

export default CalendarLabel;
