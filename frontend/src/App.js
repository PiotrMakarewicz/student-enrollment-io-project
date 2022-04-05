import "./App.css";
import LecturerForm from "./components/form/LecturerForm";

function App() {
  return (
    <>
      <div className="App">
        <header className="App-header py-2 px-3">
          <div>Strona Główna</div>
          <ul>
            <li>Utwórz nową ankietę</li>
            <li>Twoje ankiety</li>
            <li>Ustawienia</li>
          </ul>
        </header>
        <div className="App-body">
          <LecturerForm />
        </div>
      </div>
    </>
  );
}

export default App;
