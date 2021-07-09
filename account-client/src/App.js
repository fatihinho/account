import './App.css';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import AppNavbar from "./AppNavbar";

import Home from './Home';
import CustomerList from './CustomerList';

export default function App() {
  return (
    <div>
      <Router>
        <AppNavbar />
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/customers" exact={true} component={CustomerList} />
        </Switch>
      </Router>
    </div>
  );
}