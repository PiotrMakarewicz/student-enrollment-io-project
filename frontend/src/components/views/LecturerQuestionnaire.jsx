import React from "react";
import SimpleWrapper from "../SimpleWrapper";
import ViewSwitch from "./ViewSwitch/ViewSwitch";
import { useParams } from "react-router-dom";
import FloatingActionButton from "../form/services/FloatingActionButton";

function LecturerQuestionaire() {
    const { id } = useParams();

    return (
        <SimpleWrapper>
            <ViewSwitch
                values={["Results", "Groups", "Impossibility"]}
                selected_="Results"
            />
            <div className="lecturer-questionnaire-buttons">
                <div>
                    <span>File with user preferences</span>
                    <a href={`http://localhost:8080/files/preferences/english/${id}`}>download</a>
                </div>
                <div>
                    <span>File with user groups </span>
                    <a href={`http://localhost:8080/files/results/english/${id}`}>download</a>
                </div>
                <div>
                    <span>File with user links </span>
                    <a href={`http://localhost:8080/files/links/${id}`}>download</a>
                </div>
            </div>
            <FloatingActionButton />
        </SimpleWrapper>
    );
}

export default LecturerQuestionaire;
