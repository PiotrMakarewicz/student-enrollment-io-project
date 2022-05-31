import React, { useEffect, useState } from "react";
import http from "../../../services/http";
import { Spinner } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { DayHeader, TermHeader, GroupBody } from "../../group";
import "./impossibilities.css";

/**
 * @description function returning view with impossibilities in current questionnaire
 *
 * @example <ImpossibilitiesAcceptance/>
 *
 */

function ImpossibilitiesAcceptance() {
    const { id } = useParams();
    const [state, setState] = useState({
        data: [],
        loading: true,
        hasData: false
    });
    
    const daysArr = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const sortDays = function (a, b) {
        a = daysArr.indexOf(a.name);
        b = daysArr.indexOf(b.name);
        return a < b ? -1 : 1;
    };


    useEffect(() => {
        (async function () {
            const response = await http.get(`/impossibilities/${id}`);
            if (response.ok) {
                {console.log(response);}
                setState({
                    ...state,
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

    // const days = [];
    // state.data.forEach((el) => {
    //     const student = `${el.student.firstName} ${el.student.lastName}`.trim();
    //     if (days.some((e) => e.name === el.term.day)) {
    //         let day = days.find((e) => e.name === el.term.day);
    //         if (!day.terms.some((e) => e.timeslot.id === el.term.timeslot.id)) {
    //             day.terms = [...day.terms, { timeslot: el.term.timeslot, students: [student] }];
    //             day.termsCount += 1;
    //         } else {
    //             let term = day.terms.find((e) => e.timeslot.id === el.term.timeslot.id);
    //             term.students = [...term.students, student];
    //             if (term.students.length > maxGroupSize) maxGroupSize = term.students.length;
    //         }
    //     } else {
    //         const day = {
    //             name: el.term.day,
    //             terms: [{ timeslot: el.term.timeslot, students: [student] }],
    //             termsCount: 1
    //         };
    //         days.push(day);
    //     }
    // });

    // const cols = Array.from(days).sort(sortDays);

    // let rows = [];
    // for (let count = 0; count < maxGroupSize; count++) {
    //     let row = [];
    //     for (let i = 0; i < cols.length; i++) {
    //         for (let j = 0; j < cols[i].termsCount; j++) {
    //             if (count >= cols[i].terms[j].students.length) {
    //                 row.push("");
    //             } else {
    //                 row.push(cols[i].terms[j].students[count]);
    //             }
    //         }
    //     }
    //     rows.push(row);
    // }

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <div>
                    {state.data.votes.map((vote, key) =>{
                        <input className="default" readOnly={true} defaultValue={vote.questionnaire.label}/>
                    })

                    }
                </div>
            )}
        </>
    );
}

export default ImpossibilitiesAcceptance;
