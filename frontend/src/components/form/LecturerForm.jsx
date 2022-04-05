import React, { useState } from "react";
import DatePicker from "react-widgets/DatePicker";
import TimeInput from "react-widgets/TimeInput";
import "react-widgets/styles.css";

import { Input } from "./basic";
import Submit from "./basic/Submit";

/**
 * Lecturer form
 *
 * @memberof Form
 *
 */

function LecturerForm() {
  const [state, setState] = useState({
    name_input: "",
    fullname_input: "",
    date_input: new Date(),
    time_input: new Date(),
  });

  const onSubmit = () => {
    console.log(state);
    setState({
      name_input: "",
      fullname_input: "",
      date_input: new Date(),
      time_input: new Date(),
    });
    // TODO add endpoint to send input data to server (.json)
  };

  return (
    <form className="d-flex flex-column items-start w-50">
      <Input
        type={"text"}
        label="Nazwa przedmiotu"
        value={state.name_input}
        onChange={(v) => setState({ ...state, name_input: v })}
        id={"name_input"}
      />
      <Input
        type={"text"}
        label="Imię i nazwisko prowadzącego"
        value={state.fullname_input}
        onChange={(v) => setState({ ...state, fullname_input: v })}
        id={"fullname_input"}
        placeholder={""}
      />

      <div className="flex flex-row my-2 mw-75">
        <label>Data wygaśnięcia ankiety</label>
        <DatePicker
          defaultValue={new Date()}
          className="mt-1"
          value={state.date_input}
          onChange={(v) => setState({ ...state, date_input: v })}
        />
        <TimeInput
          defaultValue={null}
          className="mt-1"
          value={state.time_input}
          onChange={(v) => setState({ ...state, time_input: v })}
        />
      </div>
      <Submit value={"Dalej"} onSubmit={onSubmit} />
    </form>
  );
}

export default LecturerForm;
