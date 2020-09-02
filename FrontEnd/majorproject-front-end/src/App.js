import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import "./App.css";

import Employee from "./Components/employee/Employee";
import Customer from "./Components/customer/Customer";
import Header from "./Components/layout/Header.js";
import HomePage from "./Components/homepage/HomePage";
import Signup from "./Components/account/Signup.js";
import Login from "./Components/account/Login.js";
import StaffLogin from "./Components/account/StaffLogin";
import Booking from "./Components/booking/Booking";
import AboutUs from "./Components/aboutUs/AboutUs"

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      //this will boolean at some point but can change later
      loggedInStatus: "NOT_LOGGED_IN",
      employee: false,
      customer: false,
      user: {},
    };
  }

  customerAuth = (data) => {
    this.setState({
      user: data,
      customer: true,
      loggedInStatus: "LOGGED_IN"
    })
  }

  employeeAuth = (data) => {
    this.setState({
      user: data,
      employee: true,
      loggedInStatus: "LOGGED_IN"
    })
  }
  
  toggleLogout = () => {
    this.setState({
      loggedInStatus: "NOT_LOGGED_IN",
      customer: false,
      employee: false,
      user: {}
    })
  }

  render() {
    return (
      <Router>
        <div>
          <Header toggleLogout={this.toggleLogout} 
                  customer={this.state.customer} 
                  employee={this.state.employee} 
                   />

          <Route
            exact
            path="/"
            render={(props) => (
              <HomePage {...props} 
                        loggedInStatus={this.state.loggedInStatus} />
            )}
          />

          <Route exact path="/aboutus" component={AboutUs}/>

          <Route
            path="/login"
            render={(props) => (
              <Login {...props} 
                     customerAuth={this.customerAuth} 
                     loggedInStatus={this.state.loggedInStatus} />
            )}
          />

          <Route
            path="/createAccount"
            render={(props) => (
              <Signup {...props} 
                      loggedInStatus={this.state.loggedInStatus} />
            )}
          />

          <Route
            path="/staffLogin"
            render={(props) => (
              <StaffLogin
                {...props}
                loggedInStatus={this.state.loggedInStatus}
                employeeAuth={this.employeeAuth}
              />
            )}
          />

          <Route
            path="/booking"
            render={(props) => (
              <Booking {...props} 
                       loggedInStatus={this.state.loggedInStatus} />
            )}
          />

          <Route
            path="/customer"
            render={(props) => (
              <Customer {...props} 
                        loggedInStatus={this.state.loggedInStatus} />
            )}
          />

          <Route
            path="/employee"
            render={(props) => (
              <Employee {...props}
                        loggedInStatus={this.state.loggedInStatus} />
            )}
          />
        </div>
      </Router>
    );
  }
}

export default App;
