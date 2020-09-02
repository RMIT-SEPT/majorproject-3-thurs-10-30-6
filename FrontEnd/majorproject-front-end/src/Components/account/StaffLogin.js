import React, { Component } from 'react'
import Axios from "axios";
import './Account.css';
import { Redirect } from 'react-router-dom';

class StaffLogin extends Component {
    constructor(props){
        super(props);

        this.state = {
            username: "",
            password: "",
            loggedIn: false
        }

    }

    toggleGet = (username, password) => {
        Axios.get("http://localhost:8080/api/employee/" + username + "/" + password, {
        })
        .then(res => {
            this.setState({
                loggedIn: true
            })

            this.props.employeeAuth(res.data);
        }).catch(e => {
            if(e.response.status === 400){
                alert("Username or password incorrect");
            }
        })
    }

    handleSubmit = (e) => {
        this.toggleGet(this.state.username, this.state.password);
        e.preventDefault();
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    
    render() {
        if(this.state.loggedIn === true){
            return <Redirect to={{pathname: "/"}}/>
        }

        return (
                
            <div className="container">
                <h1>STAFF LOGIN</h1>
                <form onSubmit={this.handleSubmit}>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Username:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="username" placeholder="Username" value={this.state.username} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Password:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="password" name="password" placeholder="Password" value={this.state.password} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <button type="submit" value="Submit">Log in</button>
                </form>
            </div>

        )
    }
}
export default StaffLogin;
