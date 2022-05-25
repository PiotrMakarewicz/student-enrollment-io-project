import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";
import Navbar from "./components/Navbar";
import { ChooseForm, ClientViewForm, LecturerForm, LecturerQuestionaire } from "./components/views";

function App() {
    return (
        <>
            <Router>
                <div className="App">
                    <Navbar />

                    <div className="App-body">
                        <div
                            className="alertContainer"
                            data-alert-container
                        >
                            <div className="alert hide"></div>
                        </div>
                        <Routes>
                            <Route
                                exact
                                path="/"
                                element={<Navigate to="/addQuestionnaire" />}
                            ></Route>
                            <Route
                                path="/questionnaires"
                                element={<ChooseForm />}
                            />
                            <Route
                                path="/vote/:id"
                                element={<ClientViewForm />}
                            />
                            <Route
                                path="/addQuestionnaire"
                                element={<LecturerForm />}
                            />
                            <Route
                                path="/questionnaire/:id"
                                element={<LecturerQuestionaire />}
                            />

                            <Route
                                path="*"
                                element={<h2>Page not found</h2>}
                            />
                        </Routes>
                    </div>
                </div>
            </Router>
            <ToastContainer />
        </>
    );
}

export default App;
