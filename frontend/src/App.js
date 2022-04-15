import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LecturerForm from "./components/form/LecturerForm/LecturerForm";
import Navbar from "./components/Navbar";

function App() {
    return (
        <>
            <Router>
                <div className="App">
                    <Navbar />
                    <div className="App-body">
                        <Routes>
                            <Route
                                path="/new-form"
                                element={<LecturerForm />}
                            />
                            <Route
                                path="*"
                                element={<h2>Page not found</h2>}
                            />
                        </Routes>
                    </div>
                </div>
            </Router>
        </>
    );
}

export default App;
