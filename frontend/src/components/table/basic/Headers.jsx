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
        {headers.map((head, key) => (
          <th key={key}>{head}</th>
        ))}
      </tr>
    </thead>
  );
}

export default Headers;
