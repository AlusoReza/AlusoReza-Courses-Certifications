// This is the main JavaScript file for the React application. It defines the App component, which is the root component of the application. The App component renders a header section with a logo, some text, and a link to the React documentation. The logo is imported from a local file and displayed using an <img> tag. The App component is exported as the default export of this module, so it can be imported and used in other parts of the application.

import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
