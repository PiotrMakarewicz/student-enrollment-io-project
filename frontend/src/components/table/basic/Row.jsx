import React from "react";
import Cell from './Cell';

/**
 *
 * Forms rows in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param   {Row<{}>[]} rows  Rows
 * @param   {(row: Row<{}>) => void} prepareRow  preparsRows get from useTable
 * 
 * 
 * @example
 * 
 *  <Row 
        rows={rows}
    />
 */


function Row({rows, prepareRow}){
    return(
        <>
        {rows.map((row) => {
            prepareRow(row)
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => 
                  <Cell cell={cell}/>
                  )}
              </tr>
            )
          })}
        </>
    );
}

export default Row;