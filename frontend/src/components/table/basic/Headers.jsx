import React from "react";

/**
 *
 * Forms header in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param  {Array.<string>} Headers Array of headers 
 * 
 * 
 * @example
 * 
 *  <Headers 
        headers={headers}
    />
 */

function Headers({ headers }) {
    return (
        <thead className="thead">
            <tr>
                <th>{headers ? "Students index" : ""}</th>
                {headers.map((label, key) => (
                    <th key={key}>{label}</th>
                ))}
            </tr>
        </thead>
    );
}

export default Headers;
