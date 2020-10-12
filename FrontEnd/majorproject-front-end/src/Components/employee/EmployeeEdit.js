import React, { Component } from "react";

class EmployeeEdit extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
            selectedUser: {}
        };
      }

    isAdminUser() {
        if(this.props.userAuth != null) {
            return this.props.userAuth.admin === true;
        } else {
            return false;
        }
            
    }

    render() {
        
        if (this.isAdminUser()) {
            return(
                <div className="container">
                <h1>Sign Up</h1>
                <form onSubmit={this.handleSubmit}>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Firstname:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="firstName" pattern="([A-Za-z])+([A-Za-z])+([A-Za-z])+" title="Three or more letter name" placeholder="First Name" value={this.state.firstName} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Lastname:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="lastName" pattern="([A-Za-z])+([A-Za-z])+([A-Za-z])+" title="Three or more letter Last name" placeholder="Last Name" value={this.state.lastName} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Email:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="email" name="email" placeholder="Email" value={this.state.email} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Address:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="address" placeholder="Address" value={this.state.address} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Phone Number:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="phoneNumber" pattern="[0-9]*" title="please only use numbers in the phonenumber" placeholder="Phone Number" value={this.state.phoneNumber} onChange={this.handleChange} required/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-1">
                            <label>
                                Username:
                            </label>
                        </div>
                        <div className="col-2">
                            <input type="text" name="userName" pattern="([A-Z]|[a-z]|[0-9])+([A-Z]|[a-z]|[0-9])+([A-Z]|[a-z]|[0-9])+" title="Must be at least three charcters long and must only contain lowercase, upercase and numbers only" placeholder="Username" value={this.state.userName} onChange={this.handleChange} required/>
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

                    <div className="row">
                    <div className="col-1">
                        <label>
                            Confirm Password:
                        </label>
                    </div>
                    <div className="col-2">
                        <input type="password" name="confirmPassword" placeholder="Confrim Password" value={this.state.confirmPassword} onChange={this.handleChange} required/>
                    </div>
                </div>

                    <button type="submit" value="Submit">Update</button>
                </form>
            </div>
            );
        } else {
            return(
                <div className="container">
                    <h1>You must be signed in with an Administrator account to access this feature.</h1>
                </div>
            );
        }
    }

}

export default EmployeeEdit