import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './App.css';
import ChooseForm from "./components/form/choose/ChooseForm";

function App() {
  return (
    <>
      <Router>
        <div className="App">
          <header className="App-header py-2 px-3">
            <Link to="/">Strona główna</Link>
            <ul>
              <li>
                <Link to="/new-form">Nowa ankieta</Link>
              </li>
              <li>
                <Link to="/my-forms">Twoje ankiety</Link>
              </li>
              <li>
                <Link to="/settings">Ustawienia</Link>
              </li>
            </ul>
          </header>
          <div className="App-body">
            <Routes>
              <Route path="/my-forms" element={<ChooseForm/>} />
              <Route path="*" element={<h2>Page not found</h2>} />
            </Routes>
          </div>
        </div>
      </Router>
    </>
  );
}

export default App;
