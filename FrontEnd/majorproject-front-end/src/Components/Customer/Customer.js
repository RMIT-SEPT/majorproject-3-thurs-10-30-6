import React, { Component } from "react";
import "./Customer.css";

class Customer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "Dulshan",
      lastName: "Dulshan",
      address: "Dulshan",
      email: "Dulshan",
      mobileNumber: "Dulshan",
      userName: "Dulshan",
      oldPassword: "",
      newPassword: "",
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {}

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  render() {
    var bookings = [
      ["1", "20/10/2020", "10.00am", "Hair cut"],
      ["1", "20/10/2020", "11.00am", "Manicure"],
      ["1", "20/10/2020", "12.00pm", "something else"],
      ["1", "20/10/2020", "1.00pm", "something else"],
      ["1", "20/10/2020", "2.00pm", "something else"],
      ["1", "20/10/2020", "3.00pm", "something else"],
    ];

    var bookingsDisplayArray = bookings.map((index) => (
      <p>{index[1] + " " + index[2] + " " + index[3] + " "}</p>
    ));

    return (
      <div>
        <h1 className="BookingsTitle"> My Bookings </h1>

        {/* the things should loop here but i have no idea how to do it */}
        <ul className="Bookings">{bookingsDisplayArray}</ul>

        <button className="cancelBtn" name="Cancel">
          Cancel
        </button>

        <p className="SpecialInstructions">
          *select the booking and then click on cancel
        </p>

        {/* the attribute value called dulshan should be changed into whatever is in the list/array */}
        <form className="FormAttribute" method="get">
          <label>
            First Name:
            <input
              value={this.state.firstName}
              type="text"
              name="firstName"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            Last Name:
            <input
              value={this.state.lastName}
              type="text"
              name="lastName"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            Address:
            <input
              value={this.state.address}
              type="text"
              name="address"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            Email:
            <input
              value={this.state.email}
              type="text"
              name="email"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            Mobile Number:
            <input
              value={this.state.mobileNumber}
              type="text"
              name="mobileNumber"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            User Name:
            <input
              value={this.state.userName}
              type="text"
              name="userName"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            Old Password:
            <input
              value={this.state.oldPassword}
              placeholder="Enter the old password"
              type="text"
              name="oldPassword"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <label>
            New Password:
            <input
              value={this.state.newPassword}
              placeholder="Enter the new password"
              type="text"
              name="newPassword"
              size="100"
              onChange={this.handleChange}
              required
            />
          </label>
          <input className="UpdateButton" type="submit" value="Update" />
        </form>
      </div>
    );
  }
}
export default Customer;
