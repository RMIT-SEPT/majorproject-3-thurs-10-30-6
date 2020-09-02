import React, { Component } from 'react'
import './Booking.css';
import '../account/Account.css'
class Booking extends Component {

    handleSubmit(event) 
    {

    }

    render() {
        return (
           <body>
                <main>
                        
                        <div className = "container">
                        <h1 className = "BookingTitle">Make A Booking</h1>
                            <form onSubmit={this.handleSubmit}>

                                <div className="row">
                                    <label className="col-1">
                                        Service:
                                    </label>
                                    <input type="text" className="col-2">

                                    </input>
                                </div>

                                <div className="row">
                                    <label className="col-1">
                                        Employee:
                                    </label>
                                    <input type="date" className="col-2">

                                    </input>
                                </div>

                                <div className="row">
                                    <label className="col-1">
                                        Booking Date:
                                    </label>
                                    <input type="date" className="col-2">

                                    </input>
                                </div>

                                <div className="row">
                                    <label className="col-1">
                                        Booking Time:
                                    </label>
                                    <input type="text" className="col-2">

                                    </input>
                                </div>
                                <div className="row"></div>
                                <input className="updateButton" type="submit" value="Submit"/>
                                
                            </form>
                        </div>
                </main>
           </body>
        )
    }
}
export default Booking;
