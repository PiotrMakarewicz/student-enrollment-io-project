import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import http from "../../../services/http";
import "./choose.css";
import SimpleWrapper from "../../SimpleWrapper";
import { Spinner } from "react-bootstrap";
import FloatingActionButton from "../../form/services/FloatingActionButton";
import DeletionConfirmation from "./DeletionConfirmation";
import { toast } from "react-toastify";

/**
 * Example form
 *
 * <ChooseForm/>
 *
 * @memberOf Form
 *
 *
 */

function createListElement(id, name, date, onDeletion) {
    var badgeType = "";
    var badgeValue = "";
    var state = 0;
    state = getStateFromDate(date);
    if (state === 0) {
        badgeType = "badge bg-warning rounded-pill align-middle";
        badgeValue = "otwarta";
    } else if (state === 1) {
        badgeType = "badge bg-success rounded-pill align-middle";
        badgeValue = "zakończona";
    } else if (state === 2) {
        badgeType = "badge bg-primary rounded-pill";
        badgeValue = "w trakcie podziału";
    } else if (state === 3) {
        badgeType = "badge bg-danger rounded-pill";
        badgeValue = "nieudany podział";
    }
    return (
        <div
            className="rowContainer"
            key={id}
        >
            <Link
                to={"/questionnaire/" + id}
                className="list-group-item d-flex list-group-item-action justify-content-between"
                key={id}
            >
                {name}
                <div className="badgeAndDelete">
                    <span className={badgeType}>{badgeValue}</span>
                    <div className="deleteButton">
                        <button
                            onClick={(e) => {
                                e.preventDefault();
                                onDeletion({ id, name });
                            }}
                        >
                            {"\u2715"}
                        </button>
                    </div>
                </div>
            </Link>
        </div>
    );
}

function getStateFromDate(date) {
    var today = new Date();
    var given = new Date(date);
    if (given > today) {
        return 0;
    }
    return 1;
}

function ChooseForm() {
    // TODO: (potentially) form state can to be calculated on backend using [expiration date] and [student-form] relation (if states 2 and 3 were to be implemented)
    // form state: 0 - form is in process of taking answers
    // 1 - form finished taking answers and results were calculated
    // 2 - app is calculating results
    // 3 - app could not divide students into groups

    // I don't know if states 2 and 3 will be used, but they are implemented here nevertheless

    const [state, setState] = useState({
        forms: [],
        loading: true,
        deleting: false,
        deletingId: -1,
        deletingName: null
    });
    useEffect(() => {
        (async function () {
            const response = await http.get("/questionnaires");
            if (response.ok) {
                setState({
                    ...state,
                    forms: (await http.get("/questionnaires")).data,
                    loading: false
                });
            }
        })();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [state.deleting]);

    const onDeletion = async (id, name) => {
        setState({
            ...state,
            deleting: true,
            deletingId: id,
            deletingName: name
        });
    };

    const onConfirmation = async (id) => {
        setState({
            ...state,
            loading: true
        });
        const response = await http.deleteH(`/questionnaires/${id}`);
        if (response.ok) {
            setState({
                ...state,
                loading: false,
                deleting: false
            });
            toast.success("Questionnaire deleted", {
                position: "top-center",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined
            });
        } else {
            setState({
                ...state,
                loading: false,
                deleting: false
            });
            toast.error(
                "Could not delete questionnaire.\nTry again later or contact the server administrator.",
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

    var rows = [];
    state.forms.forEach((element) =>
        rows.push(createListElement(element.id, element.label, element.expirationDate, onDeletion))
    );

    return (
        <>
            {state.loading ? (
                <>
                    <Spinner animation="border" />
                </>
            ) : (
                <>
                    <SimpleWrapper>
                        <ul className="list-group main-list">{rows}</ul>
                    </SimpleWrapper>
                    <FloatingActionButton />
                    {state.deleting ? (
                        <DeletionConfirmation
                            questionnaireId={state.deletingId}
                            onRefusal={() => {
                                setState({
                                    ...state,
                                    deleting: false
                                });
                            }}
                            onConfirmation={() => {
                                onConfirmation(state.deletingId.id);
                            }}
                        />
                    ) : (
                        <></>
                    )}
                </>
            )}
        </>
    );
}

export default ChooseForm;
