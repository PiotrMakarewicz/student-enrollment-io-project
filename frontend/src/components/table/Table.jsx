import React from "react";
import { Headers, Row } from "./basic";

/**
 *
 * Forms a table
 *
 * Table should be import from index.js, to load propely css stylesheet.
 *
 *
 * @memberOf Table
 * @param   {Array.<Array.<string>>} headers  Array of arrays of headers [[head1],[head2]]
 * @param   {Array.<Array.<string>>} records  Array of records
 * @param   {function(string):string} lambda   Function that modifies cells of array
 *
 *
 * @example
 *
 *  <Table headers={headers} records={records} lambda={cell => cell=="Yes"? "bg-success": ""} />
 */

function Table({ headers, records, lambda }) {
    return (
        <table
            className="table table-results"
            striped={"true"}
            bordered={"true"}
            hover={"true"}
        >
            <Headers headers={headers} />
            <Row
                records={records}
                lambda={lambda}
            />
        </table>
    );
}

export default Table;
