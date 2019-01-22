import React, { Component } from 'react';
import { BrowserRouter, Route, Link } from 'react-router-dom';
import logo from './rhm.png';
import Welcome from './Welcome';
import Secured from './Secured';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />

          <BrowserRouter>
            <div className="container">
              <ul>
                <li><Link to="/">public component</Link></li>
                <li><Link to="/secured">secured component</Link></li>
              </ul>
              <Route exact path="/" component={Welcome} />
              <Route path="/secured" component={Secured} />
            </div>
          </BrowserRouter>
        </header>
      </div>
    );
  }
}

export default App;
