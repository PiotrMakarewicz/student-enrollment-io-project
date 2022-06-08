import React from "react";
import { Cell } from ".";

/**
 *
 * Forms records in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param  {Array.<Array.<string>>} records  Array of records
 * @param  {function(string):string} lambda  Function that modifies cells of array
 *
 * 
 * 
 * @example
 * 
 *  <Row 
        rows={rows} lambda={lambda}
    />
 */

function Row({ records, lambda }) {
    return (
        <tbody>
            {records.map((record, key) => {
                return (
                    <tr key={key}>
                        <>
                            <Cell
                                cell={record.student.indexNumber}
                                key={key}
                                lambda={lambda}
                            />
                            {record.studentChoose.map((cell, key) => (
                                <Cell
                                    cell={cell}
                                    key={key}
                                    lambda={lambda}
                                />
                            ))}
                        </>
                    </tr>
                );
            })}
        </tbody>
    );
}

export default Row;
