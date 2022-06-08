import React from "react";

/**
 * Creates body for the groups table
 *
 * @memberof Group
 * @param {Array.<Array>} rows Array of students
 *
 */

function GroupBody({ rows }) {
    return (
        <>
            {rows.map((row, key) => {
                return (
                    <tr key={key}>
                        {row.map((el, key) => {
                            return (
                                <td
                                    className="bg-light"
                                    key={key}
                                >
                                    {el}
                                </td>
                            );
                        })}
                    </tr>
                );
            })}
        </>
    );
}

export default GroupBody;
