import React from "react";

/**
 * Creates day name header for the groups table
 *
 * @memberof Group
 * @param {Array} cols Array of the days with generated groups
 *
 */

function DayHeader({ cols }) {
    return (
        <>
            <tr>
                {cols.map((day, key) => {
                    return (
                        <th
                            key={key}
                            colSpan={day.termsCount}
                        >
                            {day.name}
                        </th>
                    );
                })}
            </tr>
        </>
    );
}

export default DayHeader;
