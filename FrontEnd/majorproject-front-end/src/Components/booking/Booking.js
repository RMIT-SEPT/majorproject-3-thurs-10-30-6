import React, { Component } from "react";
import "./Booking.css";
import "../account/Account.css";
import Axios from "axios";
import { Redirect } from "react-router-dom";

class Booking extends Component {
  constructor(props) {
    super(props);
    this.state = {
      services: [],
      employees: [],
      bookingTimes: [],
      alreadyBooked: [],
      selectedService: "",
      selectedEmployee: "",
      selectedDate: "",
      selectedTime: "",
      buttonDisabled: true,
      employeeDisabled: true,
      dateDisabled: true,
      timeDisabled: true,
      serviceTypeDetails: [],
    };
    //retrieves all front end services
    Axios.get("http://3.237.224.176:8080/api/serviceType/all", {})
      .then((res) => {
        this.setState({ services: res.data });
      })
      .catch((error) => {
        console.log(error.response.status);
        alert(
          "An error occured, it seems the backend cannot be reached or no services are present in our backend"
        );
      });
  }

  componentDidMount = () => {};

  handleSubmit = (event) => {
    var postData = {};
    postData["employeeIdentifier"] = this.state.selectedEmployee;
    postData["customerIdentifier"] = this.props.user.identificationNumber;
    postData["serviceNo"] = this.state.selectedService;
    postData["rosterTime"] = this.state.selectedTime;
    postData["rosterDate"] = this.state.selectedDate;
    Axios.post("http://3.237.224.176:8080/api/booking/newBooking", postData)
      .then((res) => {
        alert(res.data);
      })
      .catch((error) => {
        console.log(error.response.status);
        alert("An error occured, you booking was not created");
      });
    this.setState({ employees: [] });
    this.setState({ employeeDisabled: true });
    this.setState({ dateDisabled: true });
    this.setState({ bookingTimes: [] });
    this.setState({ buttonDisabled: true });
    this.setState({ timeDisabled: true });
    event.preventDefault();
  };

  handleServiceSelection = (event) => {
    //Set the state of the service
    this.setState({ selectedService: event.target.value });
    //Checking if the selected option is not the "none" attribute
    if (event.target.value !== "none") {
      Axios.get(
        "http://3.237.224.176:8080/api/employee/all/" + event.target.value,
        {}
      )
        .then((res) => {
          var employees = res.data;
          employees = employees.filter((emp) => !emp.admin);
          this.setState({ employees: employees });
          this.setState({ employeeDisabled: false });
          this.setState({ dateDisabled: true });
          this.setState({ bookingTimes: [] });
          this.setState({ buttonDisabled: true });
          this.setState({ timeDisabled: true });
        })
        .catch((error) => {
          alert("An error occured, details : " + error.response.status);
        });

      Axios.get(
        "http://3.237.224.176:8080/api/serviceType/" + event.target.value,
        {}
      )
        .then((res) => {
          //passers GET data to app.js
          this.setState({ serviceTypeDetails: res.data });
        })
        .catch((e) => {
          this.setState({
            error: true,
          });
          if (this.state.error === true) {
            console.log(
              "Something went wrong in booking.js in get handleServiceSelection, getting service details"
            );
          }
        });
    } else {
      this.setState({ employees: [] });
      this.setState({ employeeDisabled: true });
      this.setState({ dateDisabled: true });
      this.setState({ bookingTimes: [] });
      this.setState({ buttonDisabled: true });
      this.setState({ timeDisabled: true });
    }
  };

  handleEmployeeSelection = (event) => {
    this.setState({ selectedEmployee: event.target.value });
    if (event.target.value !== "none") {
      this.setState({ dateDisabled: false });
      this.setState({ buttonDisabled: true });
      this.setState({ timeDisabled: true });
      this.setState({ times: [] });
    } else {
      this.setState({ dateDisabled: true });
      this.setState({ buttonDisabled: true });
      this.setState({ bookingTimes: [] });
    }
  };

  handleDateSelection = (event) => {
    var date = new Date(event.target.value);
    var today = new Date();
    var nextWeek = new Date(
      today.getFullYear(),
      today.getMonth(),
      today.getDate() + 8
    );
    //If the selected date is in the future, proceed
    if (
      today.getTime() < date.getTime() &&
      date.getTime() < nextWeek.getTime()
    ) {
      var flag = true;
      var weekDay = date.getDay();
      var employee = this.state.employees.find((employee) => {
        return employee.employeeIdentifier === this.state.selectedEmployee;
      });
      switch (weekDay) {
        case 0:
          if (!employee.roster.sunday) {
            flag = false;
          }
          break;
        case 1:
          if (!employee.roster.monday) {
            flag = false;
          }
          break;
        case 2:
          if (!employee.roster.tuesday) {
            flag = false;
          }
          break;
        case 3:
          if (!employee.roster.wednesday) {
            flag = false;
          }
          break;
        case 4:
          if (!employee.roster.thursday) {
            flag = false;
          }
          break;
        case 5:
          if (!employee.roster.friday) {
            flag = false;
          }
          break;
        case 6:
          if (!employee.roster.saturday) {
            flag = false;
          }
          break;
        default:
          flag = true;
      }
      if (flag) {
        //Formatting the date to the correct date
        var year = "" + date.getFullYear();
        var month = "" + (date.getMonth() + 1);
        var day = "" + date.getDate();
        if (month.length < 2) {
          month = "0" + month;
        }
        if (day.length < 2) {
          day = "0" + day;
        }
        var formattedDate = year + "-" + month + "-" + day;
        this.setState({ selectedDate: formattedDate });
        var endTime = parseInt(this.state.serviceTypeDetails.endTime);
        var startTime = parseInt(this.state.serviceTypeDetails.startTime);
        var times = [];
        for (var i = startTime; i < endTime; i++) {
          times.push(i + ":00");
        }
        //GET request determines which times the employee is already booked for on the day
        Axios.get(
          "http://3.237.224.176:8080/api/booking/" +
            formattedDate +
            "/" +
            this.state.selectedEmployee,
          {}
        )
          .then((res) => {
            this.setState({ alreadyBooked: res.data }, function () {
              var booked = res.data;
              for (let i = 0; i < booked.length; i++) {
                times = times.filter((item) => item !== booked[i].time);
              }
              this.setState({ bookingTimes: times });
            });
            this.setState({ timeDisabled: false });
          })
          .catch((e) => {
            //If no booking times are detected, then no times are removed from the selection list.
            if (e.response.status === 400) {
              this.setState({ timeDisabled: false });
              this.setState({ bookingTimes: times });
            }
          });
      } else {
        this.setState({ bookingTimes: [] });
        this.setState({ buttonDisabled: true });
        this.setState({ timeDisabled: true });
        alert("Employee is not working on this day");
      }
    } else {
      this.setState({ bookingTimes: [] });
      this.setState({ buttonDisabled: true });
      this.setState({ timeDisabled: true });
      if (today.getTime() > date.getTime()) {
        alert("Please Select a Date that is Not in the past!");
      } else {
        alert("You can only select bookings withing the next 7 days");
      }
    }
  };

  handleTimeSelection = (event) => {
    //Make sure the selected value is a relevant one
    if (event.target.value !== "none") {
      this.setState({ selectedTime: event.target.value + ":00" });
      this.setState({ buttonDisabled: false });
    } else {
      this.setState({ buttonDisabled: true });
    }
  };

  render() {
    if (this.props.loggedInStatus === "LOGGED_IN") {
      return (
        <div className="container">
          <h1 className="BookingTitle">Make A Booking</h1>
          <form onSubmit={this.handleSubmit}>
            <div className="row">
              <div className="col-1">
                <label>Service:</label>
              </div>
              <div className="col-2">
                <select
                  name="ServiceNo"
                  className="ServiceNo"
                  value={this.state.value}
                  onChange={this.handleServiceSelection}
                >
                  <option value="none">Select an option:</option>
                  {this.state.services.map((service) => (
                    <option value={service.serviceNo}>
                      {service.serviceName}
                    </option>
                  ))}
                </select>
              </div>
            </div>
            <br></br>

            <div className="row">
              <div className="col-1">
                <label>Employee:</label>
              </div>
              <div className="col-2">
                <select
                  name="employeeNo"
                  className="employeeNo"
                  value={this.state.value}
                  onChange={this.handleEmployeeSelection}
                  disabled={this.state.employeeDisabled}
                >
                  <option value="none">Select an option</option>
                  {this.state.employees.map((employee) => (
                    <option value={employee.employeeIdentifier}>
                      {employee.firstName}
                    </option>
                  ))}
                </select>
              </div>
            </div>
            <br></br>

            <div className="row">
              <div className="col-1">
                <label>Booking Date:</label>
              </div>
              <div className="col-2">
                <input
                  type="date"
                  className="date"
                  disabled={this.state.dateDisabled}
                  onChange={this.handleDateSelection}
                ></input>
              </div>
            </div>
            <br></br>

            <div className="row">
              <div className="col-1">
                <label>Booking Time:</label>
              </div>
              <div className="col-2">
                <select
                  className="time"
                  value={this.state.value}
                  onChange={this.handleTimeSelection}
                  disabled={this.state.timeDisabled}
                >
                  <option value="none">Select an Option</option>
                  {this.state.bookingTimes.map((time) => (
                    <option value={time}>{time}</option>
                  ))}
                </select>
              </div>
            </div>
            <br></br>
            <input
              className="updateButton"
              type="submit"
              value="Submit"
              disabled={this.state.buttonDisabled}
            />
          </form>
        </div>
      );
    } else {
      return <Redirect to={{ pathname: "/" }} />;
    }
  }
}
export default Booking;
