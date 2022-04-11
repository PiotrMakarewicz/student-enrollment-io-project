import React from 'react'
import Table from './Table'

const dummy_data = [
    {
      name: "john doe",
      czw_11_20: "No",
      czw_12_50: "Yes",
      czw_14_40: "No",
      czw_16_10: "Yes"
    },
    {
      name: "john poe",
      czw_11_20: "No",
      czw_12_50: "Yes",
      czw_14_40: "No",
      czw_16_10: "No"
    },
    {
      name: "john woe",
      czw_11_20: "No",
      czw_12_50: "Yes",
      czw_14_40: "Yes",
      czw_16_10: "No"
    },
    {
      name: "john hoe",
      czw_11_20: "No",
      czw_12_50: "No",
      czw_14_40: "No",
      czw_16_10: "Yes"
    }
  ]
  const dummy_headers = [
    {
      Header: 'Students',
      accessor: 'name',
    },
    {
      Header: 'czw_11_20',
      accessor: 'czw_11_20',
    },
    {
      Header: 'czw_12_50',
      accessor: 'czw_12_50',
    },
    {
      Header: 'czw_14_40',
      accessor: 'czw_14_40',
    },
    {
      Header: 'czw_16_10',
      accessor: 'czw_16_10',
    },
  ]


function ExampleTable(){
    return (
        <div>
          <Table headers={dummy_headers} records={dummy_data} />
        </div>
      );
}
export default ExampleTable;