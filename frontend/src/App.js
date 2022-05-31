import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Navbar from "./components/Navbar";
import { ChooseForm, ClientViewForm, LecturerForm, LecturerQuestionaire } from "./components/views";
import 'react-toastify/dist/ReactToastify.css'
import LoginFormView from "./components/views/LoginFormView";
import { getLoggedUser } from './services/auth'
import Logout from "./components/Logout";
import StartPage from "./components/StartPage";

function App() {

    const user = getLoggedUser();
    console.log(user);

    return (
        <>
            <Router>
                <div className="App">
                        {user != null ? (
                            <> 
                                <Navbar />
                                <div className="App-body">
                                    <Routes>
                                        <Route
                                            path="/questionnaires"
                                            element={<ChooseForm />}
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
                                            path="/logout"
                                            element={<Logout />}
                                        />

                                        <Route
                                            path="*"
                                            element={<ChooseForm />}
                                        />

                                    </Routes>
                                    </div>
                                </>
                            ) : (
                                <>
                                <div className="App-body">
                                <Routes>
                                    <Route
                                        path="/vote/:hash"
                                        element={<ClientViewForm />}
                                    />
                                    <Route
                                        path="/login"
                                        element={<LoginFormView />}
                                    />
                                    <Route
                                        path="*"
                                        element={ <StartPage />}
                                    />
                                    </Routes>
                                </div>
                            </>
                        )}
                </div>
            </Router>
            <ToastContainer />
        </>
    );
}

export default App;
