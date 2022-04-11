import React from "react";

/**
 *
 * Forms header in the table
 * 
 * 
 * @memberOf Table.Basic
 * @param   {HeaderGroup} headerGroup  Headers got from useTable
 * 
 * 
 * @example
 * 
 *  <Headers 
        headerGroup={headerGroup}
    />
 */


function Headers({headerGroups}){
    return(
      <tr>
        {
        headerGroups[0].headers.map(column => (
          <th {...column.getHeaderProps()}>
            {column.render('Header')}
          </th>  
        ))
        }
      </tr>
    );
}

export default Headers;