import React from "react";
import {useTable}  from 'react-table'
import Headers from './basic/Headers'
import Row from "./basic/Row";

/**
 *
 * Forms a table 
 * 
 * 
 * @memberOf Table
 * @param   headers  Array of headers {Header: , accessor: }
 * @param   records  Array of records
 * 
 * 
 * @example
 * 
 *  <Table headers={dummy_headers} records={dummy_data} />
 */


 function Table({headers, records}){
    const columns = React.useMemo(
      () =>  headers,
      []
    );
    const data = React.useMemo(() => records, [])
    const {
          getTableProps,
          headerGroups,
          rows,
          prepareRow,
        } = useTable({
          columns,
          data
        });
    return(
      <table class="table" striped bordered hover size="sm" {...getTableProps()}>
          <thead>
            <Headers headerGroups={headerGroups}/>
          </thead>
          <tbody>
            <Row rows={rows} prepareRow={prepareRow}/>
          </tbody>
      </table>
    );
  }

  export default Table;