import React from "react";

/**
 * Creates terms header for the groups table
 *
 * @memberof Group
 * @param {Array} cols Array of the days with generated groups
 *
 */

function TermHeader({ cols }) {
    return (
        <>
            <tr>
                {cols.map((day) => {
                    return day.terms.map((el, key) => {
                        return (
                            <th
                                key={key}
                                scope="col"
                            >
                                {el.timeslot.startTime.slice(0, -3)}-
                                {el.timeslot.endTime.slice(0, -3)}
                            </th>
                        );
                    });
                })}
            </tr>
        </>
    );
}

export default TermHeader;
