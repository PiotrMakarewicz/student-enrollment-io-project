import CalendarLabel from "./CalendarLabel";

/**
 *
 * Creates table header of the table
 *
 * @memberof Form.Calendar
 * @param labels Array of labels ["Monday",...]
 *
 * @example <CalendarHeader labels = {["Ala","ma","kota"]}
 *
 */
function CalendarHeader({ labels }) {
    const headers = [[""], ...labels].map((r, key) => (
        <CalendarLabel
            key={key}
            label={r}
        />
    ));

    return (
        <thead>
            <tr>{headers}</tr>
        </thead>
    );
}

export default CalendarHeader;
