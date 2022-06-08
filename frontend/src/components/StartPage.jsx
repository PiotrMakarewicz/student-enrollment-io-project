import React from "react";

function StartPage() {
    return (
        <>
            <div className="startPage">
                <h1>Student enrollment &#127891;</h1>
                <div>
                    <h3>New poll</h3>
                    <p>
                        Quickly create and schedule a new questionnaire for any subject you want.
                        Select all the terms that suit you.
                    </p>
                    <em>Lecturer only. </em>
                    <em>Remember to upload the file with students information</em>
                    <hr />
                    <h3>Your polls</h3>
                    <p>
                        Check on your polls and see the feedback provided by your students. Manage,
                        generate and modify download our groups.
                    </p>
                    <em>Lecturer only</em>
                    <hr />
                    <h3>Vote</h3>
                    <p>
                        Choose the best term for your classes. In case of problems, tap the term one
                        more time to select impossibility. Remember to give your lecturer
                        information about that term
                    </p>
                    <em>Student only</em>
                </div>
            </div>
        </>
    );
}

export default StartPage;
