import React from "react";
import { Link } from "react-router-dom";

import "./choose.css";

/**
 * Example form
 *
 * <ChooseForm/>
 *
 * @memberOf Form
 *
 *
 */
// function onSubmit(id) {
//     console.log("Form " + id + " was chosen.");
//     // TODO go to the view of a chosen form
// }

function createListElement(id, name, state) {
    var badgeType = "";
    var badgeValue = "";
    if (state === 0) {
        badgeType = "badge bg-warning rounded-pill";
        badgeValue = "otwarta";
    } else if (state === 1) {
        badgeType = "badge bg-success rounded-pill";
        badgeValue = "zakończona";
    } else if (state === 2) {
        badgeType = "badge bg-primary rounded-pill";
        badgeValue = "w trakcie podziału";
    } else if (state === 3) {
        badgeType = "badge bg-danger rounded-pill";
        badgeValue = "nieudany podział";
    }
    return (
        <Link
            to={"/questionnaire/" + id}
            className="list-group-item list-group-item-action"
        >
            <li className="list-group-item d-flex justify-content-between align-items-center">
                {name}
                <span className={badgeType}>{badgeValue}</span>
            </li>
        </Link>
    );
}

function ChooseForm() {
    const forms = [
        [0, "Ankieta 15555555555555555555", 0],
        [1, "Ankieta 2", 1],
        [2, "Ankieta 23", 2],
        [3, "Ankieta 24", 3]
    ];
    // TODO get a list of forms from database (current format is [form id], [form label], [form state]

    // (form state has to be calculated on backend using [expiration date] and [student-form] relation (if states 2 and 3 were to be implemented))
    // form state: 0 - form is in process of taking answers
    // 1 - form finished taking answers and results were calculated
    // 2 - app is calculating results
    // 3 - app could not divide students into groups

    // I don't know if states 2 and 3 will be used, but they are implemented here nevertheless

    var rows = [];
    forms.forEach((element) => rows.push(createListElement(element[0], element[1], element[2])));

    return <ul className="list-group main-list">{rows}</ul>;
}

export default ChooseForm;
