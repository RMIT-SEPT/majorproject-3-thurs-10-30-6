import React, { Component } from 'react'
import './Booking.css';
import '../account/Account.css'
import Axios from 'axios';
import { Redirect } from 'react-router-dom';

class Booking extends Component {

    constructor(props)
    {
        super(props)
        this.state = {
            services: [],
            employees: [],
            bookingTimes: [],
            alreadyBooked: [],
            selectedService:"",
            selectedEmployee:"",
            selectedDate:"",
            selectedTime:"",
            buttonDisabled: true,
            employeeDisabled: true,
            dateDisabled: true,
            timeDisabled:true,
        }
        Axios.get("http://localhost:8080/api/serviceType/all",{}).then(
            res => {this.setState({services:res.data})
       }
        ).catch(error => {
            console.log(error.res.data)
        })
    }

    handleSubmit = (event) =>
    {
        var postData = {}
        postData["employeeId"] = this.state.selectedEmployee
        postData["customerId"] = this.props.user.identificationNumber
        console.log(this.props.user.identificationNumber)
        Axios.post("http://localhost:8080/api/booking/newBooking/"+this.state.selectedTime+"/"+this.state.selectedDate,
        postData).then(res =>
            {alert(res.data)})
            this.setState({employees: []})
            this.setState({employeeDisabled: true})
            this.setState({dateDisabled: true})
            this.setState({bookingTimes: []})
            this.setState({buttonDisabled: true})
            this.setState({timeDisabled: true})
        event.preventDefault()
    }

    handleServiceSelection = (event) =>
    {
        console.log("props : " + this.props.user.identificationNumber)
        //Set the state of the service
        this.setState({selectedService: event.target.value})
        //Checking if the selected option is not the "none" attribute
        if(event.target.value !== "none")
        {
            Axios.get("http://localhost:8080/api/employee/all/"+event.target.value,{}).then(
            res => {this.setState({employees: res.data})
                this.setState({employeeDisabled: false})
                this.setState({dateDisabled: true})
                this.setState({bookingTimes: []})
                this.setState({buttonDisabled: true})
                this.setState({timeDisabled: true})
            }
            ).catch(error => {
                alert("An error occured")
            })
        }else{
            this.setState({employees: []})
            this.setState({employeeDisabled: true})
            this.setState({dateDisabled: true})
            this.setState({bookingTimes: []})
            this.setState({buttonDisabled: true})
            this.setState({timeDisabled: true})
        }
        
    }

    handleEmployeeSelection = (event) =>
    {
        this.setState({selectedEmployee: event.target.value})
        console.log(event.target.value)
        if(event.target.value !== "none")
        {
            this.setState({dateDisabled: false})
            this.setState({buttonDisabled: true})
            this.setState({timeDisabled: true})
            this.setState({times:[]})
        }else{
            this.setState({dateDisabled: true})
            this.setState({buttonDisabled: true})
            this.setState({bookingTimes:[]})
        }
    }

    handleDateSelection = (event) =>
    {
        var date =new Date(event.target.value)
        var today = new Date()
        //If the selected date is in the future, proceed
        if(today.getTime() < date.getTime())
        {
            //Formatting the date to the correct date
            var year = ''+date.getFullYear();
            var month = '' + (date.getMonth() + 1);
            var day = ''+date.getDate();
            console.log(month.length)
            if(month.length < 2)
            {
                month = "0" + month
            }
            if(day.length < 2)
            {
                day = "0"+day
            }
            var formattedDate = year + "-" + month + "-"+day
            this.setState({selectedDate: formattedDate})
            console.log(formattedDate)
            var startTime = 6
            var endTime = 18
            var times = []
            for(var i = startTime; i < endTime; i++){
                times.push(i+":00")
            }
            //GET request determines which times the employee is already booked for on the day
            Axios.get("http://localhost:8080/api/booking/"+formattedDate + "/" + this.state.selectedEmployee,{}).then(
                res => {this.setState({alreadyBooked: res.data},
                    function () {var booked = res.data
                        console.log(booked.length)
                        for(i = 0; i < booked.length; i++)
                        {
                            times = times.filter(item => item !== booked[i].time)
                            console.log(booked[i].time)
                        }
                        this.setState({bookingTimes: times})
                    }

                    )
                this.setState({timeDisabled: false})}
            ).catch(e => {
                //If no booking times are detected, then no times are removed from the selection list. 
                if(e.response.status === 400){
                    this.setState({timeDisabled: false})
                    this.setState({bookingTimes: times})
                }
            })
            
        }else{
            alert("Please Select a Date that is Not in the past!")
        }

    }

    handleTimeSelection = (event) =>
    {
        console.log(event.target.value)
        //Make sure the selected value is a relevant one
        if(event.target.value !== "none")
        {
            this.setState({selectedTime: event.target.value+":00"})
            this.setState({buttonDisabled: false})
        }else{
            this.setState({buttonDisabled: true})
        }
    }

    render() {
        console.log(this.props.loggedInStatus)
        if(this.props.loggedInStatus === "LOGGED_IN")
        {
            return (
          
                <div className = "container">
                <h1 className = "BookingTitle">Make A Booking</h1>
                    <form onSubmit={this.handleSubmit}>

                        <div className="row">
                            <label className="col-1">
                                Service:
                            </label>
                            <select className="col-2" name = "ServiceNo" value={this.state.value} onChange={this.handleServiceSelection}>
                                <option value = "none">Select an option:</option>
                                {this.state.services.map((service) => (<option value = {service.serviceNo}>{service.serviceName}</option>))}
                            </select>
                        </div>

                        <div className="row">
                            <label className="col-1">
                                Employee:
                            </label>
                            <select className="col-2" name = "employeeNo" value={this.state.value} onChange={this.handleEmployeeSelection} disabled = {this.state.employeeDisabled}>
                                <option value = "none">Select an option</option>
                                    {this.state.employees.map((employee) => (<option value = {employee.employeeIdentifier}>{employee.firstName}</option>))}
                            </select>
                        </div>

                        <div className="row">
                            <label className="col-1">
                                Booking Date:
                            </label>
                            <input type="date" className="col-2" disabled = {this.state.dateDisabled} onChange={this.handleDateSelection}>

                            </input>
                        </div>

                        <div className="row">
                            <label className="col-1">
                                Booking Time:
                            </label>
                            <select type="text" className="col-2" value = {this.state.value} onChange={this.handleTimeSelection}disabled = {this.state.timeDisabled}>
                                <option value = "none">Select an Option</option>
                                    {this.state.bookingTimes.map((time) => (<option value = {time}>{time}</option>))}
                            </select>
                        </div>
                        <div className="row"></div>
                        <input className="updateButton" type="submit" value="Submit" disabled = {this.state.buttonDisabled}/>
                        
                    </form>
                </div>
            )
        }else{
            return (
          
                <Redirect to={{pathname: "/"}}/>
            )
        }
        
        
    }
}
export default Booking;
