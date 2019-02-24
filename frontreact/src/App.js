import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import User from './User';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { CookiesProvider } from 'react-cookie';

import './App.css';

class App extends Component {
  render() {
    return (
        <CookiesProvider>
          <Router>
            <Switch>
              <Route path='/' exact={true} component={Home}/>
              <Route path='/user/profile' exact={true} component={User}/>
            </Switch>
          </Router>
        </CookiesProvider>
    );
  }
}

export default App;
