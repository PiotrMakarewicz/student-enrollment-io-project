import React, { useEffect, useState } from "react";
import http from "../../../services/http";
import { Spinner } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { DayHeader, TermHeader, GroupBody } from "../../group";
import "./styles.css";

/**
 * @description function returning view with generated groups
 *
 * @example <GroupView/>
 *
 */

function GroupView() {
    const { id } = useParams();
    const [state, setState] = useState({
        lecturerFullName: "",
        data: [],
        loading: true,
        hasData: false
    });
    const [numberOfGroupsState, setNumberOfGroupsState] = useState({
        number_of_groups: 1
    });
    let maxGroupSize = 1;
    const daysArr = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const sortDays = function (a, b) {
        a = daysArr.indexOf(a.name);
        b = daysArr.indexOf(b.name);
        return a < b ? -1 : 1;
    };

    const askForResults = async () => {
        await http.get(
            `/generate_results/${id}/${
                numberOfGroupsState.number_of_groups > 0 ? numberOfGroupsState.number_of_groups : 1
            }`
        );
    };

    useEffect(() => {
        (async function () {
            const response = await http.get(`/results/${id}`);
            if (response.ok) {
                setState({
                    ...state,
                    lecturerFullName: `${response["data"][0]["questionnaire"]["teacher"].lastName} ${response["data"][0]["questionnaire"]["teacher"].firstName}`,
                    data: response["data"],
                    loading: false,
                    hasData: true
                });
            } else {
                setState({
                    ...state,
                    loading: false,
                    hasData: false
                });
            }
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id]);

    const days = [];
    state.data.forEach((el) => {
        const student = `${el.student.firstName} ${el.student.lastName}`.trim();
        if (days.some((e) => e.name === el.term.day)) {
            let day = days.find((e) => e.name === el.term.day);
            if (!day.terms.some((e) => e.timeslot.id === el.term.timeslot.id)) {
                day.terms = [...day.terms, { timeslot: el.term.timeslot, students: [student] }];
                day.termsCount += 1;
            } else {
                let term = day.terms.find((e) => e.timeslot.id === el.term.timeslot.id);
                term.students = [...term.students, student];
                if (term.students.length > maxGroupSize) maxGroupSize = term.students.length;
            }
        } else {
            const day = {
                name: el.term.day,
                terms: [{ timeslot: el.term.timeslot, students: [student] }],
                termsCount: 1
            };
            days.push(day);
        }
    });

    const cols = Array.from(days).sort(sortDays);

    let rows = [];
    for (let count = 0; count < maxGroupSize; count++) {
        let row = [];
        for (let i = 0; i < cols.length; i++) {
            for (let j = 0; j < cols[i].termsCount; j++) {
                if (count >= cols[i].terms[j].students.length) {
                    row.push("");
                } else {
                    row.push(cols[i].terms[j].students[count]);
                }
            }
        }
        rows.push(row);
    }

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <div>
                    <div className="groupView">
                        {state.hasData ? (
                            <h5>Lecturer: {state.lecturerFullName}</h5>
                        ) : (
                            <h5>Groups not yet generated</h5>
                        )}

                        <table>
                            <thead>
                                <DayHeader cols={cols} />
                                <TermHeader cols={cols} />
                            </thead>
                            <tbody>
                                <GroupBody rows={rows} />
                            </tbody>
                        </table>

                        <div className="groupsNumber">
                            <label>Number of groups: </label>
                            <input
                                type="number"
                                min={1}
                                max={20}
                                value={numberOfGroupsState.number_of_groups}
                                onChange={(v) => {
                                    console.log(typeof v.target.value);
                                    setNumberOfGroupsState({
                                        ...numberOfGroupsState,
                                        number_of_groups: parseInt(
                                            v.target.value > 20 ? 20 : v.target.value,
                                            10
                                        )
                                    });
                                }}
                                id={"number_of_groups"}
                            />
                        </div>
                        <button
                            className="generateGroupsButton"
                            onClick={askForResults}
                        >
                            Generate groups
                        </button>
                    </div>
                </div>
            )}
        </>
    );
}

export default GroupView;
