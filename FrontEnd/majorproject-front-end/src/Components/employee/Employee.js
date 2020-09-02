import React, { Component } from 'react'
import './Employee.css';
import '../account/Account.css'


class Employee extends Component {
    handleSubmit(event) {

    }

    
    render() {
        return (
                

            <div className="container">
            
            <h1>Employee</h1>
                
                <h1>Details</h1>

                <form onSubmit={this.handleSubmit}>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                First Name:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="firstname"/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Last Name:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="lastname"/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Email:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="email"/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Address:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="address"/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Mobile Number:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="username"/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Password:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="password" name="password"/>
                        </div>
                    </div>

                    
        
                    <input type="submit" value="Submit"/>
                </form>
            </div>

        )
    }
}
export default Employee;

