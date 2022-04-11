import React from "react";

/**
 *
 * Forms a Cell in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param   {Cell} cell  cell
 * 
 * 
 * @example
 * 
 *  <Cell 
        cell={cell}
    />
 */


function Cell({cell}){
  var color;
  if (cell.value === "Yes"){
    color="bg-success";
  }
  else color="";
  return(
    <td class={color} {...cell.getCellProps()}>
      {cell.render('Cell')}
    </td>
  );
}

export default Cell;