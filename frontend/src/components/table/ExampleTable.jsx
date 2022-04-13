import React from "react";
import Table from "./Table";

const dummy_data = [
  ["john doe", "No", "Yes", "No", "Yes"],
  ["john poe", "No", "Yes", "No", "No"],
  ["john woe", "No", "Yes", "Yes", "No"],
  ["john hoe", "No", "No", "No", "Yes"],
];
const dummy_headers = [
  ["Students"],
  ["th_11_20"],
  ["th_12_50"],
  ["th_14_40"],
  ["th_16_10"],
];

function ExampleTable() {
  return (
    <div>
      <Table
        headers={dummy_headers}
        records={dummy_data}
        lambda={(cell) => (cell === "Yes" ? "bg-success" : "")}
      />
    </div>
  );
}
export default ExampleTable;
