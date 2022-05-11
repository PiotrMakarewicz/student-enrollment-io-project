import React from "react";

/**
 *
 * Forms a Cell in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param   {string} cell Single cell from record
 * @param   {function(string):string} lambda Function that modifies cells of array
 * 
 * 
 * @example
 * 
 *  <Cell 
        cell={cell} lambda={lambda}
    />
 */

function Cell({ cell, lambda }) {
    return (
        <td className={lambda && lambda(cell)}>
            {cell === 1 ? "\u2713" : cell === 0 ? "\u2715" : cell}
        </td>
    );
}

export default Cell;
