import React, { useState } from "react";
import DatePicker from "react-widgets/DatePicker";
import TimeInput from "react-widgets/TimeInput";
import "react-widgets/styles.css";
import { Input } from "../form/basic";
import Submit from "../form/basic/Submit";
import FormWrapper from "../FormWrapper";
import { Calendar } from "../form/calendar";
import http from "../../services/http";
import DropZone from "../form/services/DropZone";
import Switch from "../form/services/Switch";
import readXlsxFile from "read-excel-file";
import { parseXlsxFile } from "../form/services/Parser";
import { toast } from "react-toastify";
import { Spinner } from "react-bootstrap";
import { useEffect } from "react";

/**
 *
 * Returns Lecturer form
 *
 * @memberof Form
 *
 */

function LecturerForm() {
    const [state, setState] = useState({
        name_input: "",
        fullname_input: "",
        date_input: new Date(),
        time_input: new Date(new Date().setHours(12, 0, 0, 0)),
        selected_terms: new Set(),
        terms_info: null,
        students_info: [],
        auto_sending_links: true,
        loading: true
    });

    useEffect(() => {
        (async function () {
            setState({ ...state, terms_info: (await http.get("/terms"))["data"], loading: false });
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const onSubmit = async () => {
        const {
            date_input,
            time_input,
            name_input,
            selected_terms,
            students_info,
            auto_sending_links
        } = state;

        setState({ ...state, loading: true });
        const response = await http.post("/questionnaires", {
            expirationDate: new Date(
                Date.UTC(
                    date_input.getFullYear(),
                    date_input.getMonth(),
                    date_input.getDate(),
                    !!time_input ? time_input.getHours() : 12,
                    !!time_input ? time_input.getMinutes() : 0
                )
            ),
            label: name_input,
            availableTerms: Array.from(selected_terms),
            studentsInfo: students_info,
            autoSendingLinks: auto_sending_links
        });
        setState({ ...state, loading: false });
        if (response.ok) {
            toast.success("Questionnaire created", {
                position: "top-center",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined
            });
            setState({
                ...state,
                name_input: "",
                fullname_input: "",
                date_input: new Date(),
                time_input: new Date(new Date().setHours(0, 0, 0, 0)),
                selected_terms: new Set(),
                auto_sending_links: true
            });
        } else {
            toast.error(
                "Could not create new questionnaire.\nTry again later or contact the server administrator.",
                {
                    position: "top-center",
                    autoClose: 3000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined
                }
            );
        }
    };
    function fileHandler(files) {
        let isCorrect = true;
        let v = readXlsxFile(files[0]).then((rows) => {
            let result = parseXlsxFile(rows);
            if (result == null) {
                isCorrect = false;
            }
            setState({ ...state, students_info: result });
        });

        return v.then(() => {
            return isCorrect;
        });
    }

    function handleSwitchChange() {
        const { auto_sending_links } = state;
        setState({ ...state, auto_sending_links: !auto_sending_links });
    }

    const toggleTerm = (id) => {
        const { selected_terms } = state;

        if (selected_terms.has(id)) {
            selected_terms.delete(id);
        } else {
            selected_terms.add(id);
        }
        setState({ ...state });
    };

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <>
                    <FormWrapper>
                        <title className="display-6 pb-5 pt-2">New poll</title>
                        <Input
                            type={"text"}
                            label="Subject Name"
                            value={state.name_input}
                            onChange={(v) => setState({ ...state, name_input: v })}
                            id={"name_input"}
                            maxLength="35"
                            required
                        />

                        <Input
                            type={"text"}
                            label="Lecturer Full Name"
                            value={state.fullname_input}
                            onChange={(v) => setState({ ...state, fullname_input: v })}
                            id={"fullname_input"}
                            maxLength="35"
                            required
                        />

                        <div className="row">
                            <div className="col-xs-12 col-sm-6">
                                <label htmlFor="date_picker">Expiry Date</label>
                                <DatePicker
                                    inputProps={{
                                        component: React.forwardRef((props, ref) => (
                                            <input
                                                ref={ref}
                                                {...props}
                                                readOnly
                                            />
                                        ))
                                    }}
                                    defaultValue={new Date()}
                                    min={new Date()}
                                    dropUp={true}
                                    className="mb-1"
                                    value={state.date_input}
                                    id="date_picker"
                                    onChange={(v) => setState({ ...state, date_input: v })}
                                />
                            </div>
                            <div className="col-xs-12 col-sm-6">
                                <label
                                    className="w-100"
                                    htmlFor="time_input"
                                >
                                    Expiry Time
                                </label>
                                <TimeInput
                                    defaultValue={new Date().setHours(12)}
                                    className="mb-1"
                                    style={{ width: "min(84%, 36vw, 155px)" }}
                                    value={state.time_input}
                                    id="time_input"
                                    onChange={(v) => {
                                        setState({
                                            ...state,
                                            time_input: v
                                        });
                                    }}
                                    required
                                />
                            </div>
                        </div>

                        <DropZone fileHandler={fileHandler} />
                        <Switch
                            handleSwitchChange={handleSwitchChange}
                            checked={state.auto_sending_links}
                            label_="Auto sending links"
                        />

                        <Calendar
                            selectedTerms={state.selected_terms}
                            toggleTerm={toggleTerm}
                            availableTermsSet="All"
                            impossibleTerms={{}}
                            termsInfo={state.terms_info}
                        />
                        <Submit
                            value={"Create"}
                            onSubmit={onSubmit}
                        />
                    </FormWrapper>
                </>
            )}
        </>
    );
}

export default LecturerForm;
