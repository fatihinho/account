import './App.css';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import AppNavbar from "./components/AppNavbar";

import HomePage from './pages/HomePage';
import CustomerListPage from './pages/CustomerListPage';
import NewCustomerPage from './pages/NewCustomerPage';
import CustomerPage from './pages/CustomerPage';

export default function App() {
  return (
    <div>
      <Router>
        <AppNavbar />
        <Switch>
          <Route path="/" exact={true} component={HomePage} />
          <Route path="/customers" exact={true} component={CustomerListPage} />
          <Route path="/customers/:id" exact={true} component={CustomerPage} />
          <Route path="/new-customer" exact={true} component={NewCustomerPage} />
        </Switch>
      </Router>
    </div>
  );
}