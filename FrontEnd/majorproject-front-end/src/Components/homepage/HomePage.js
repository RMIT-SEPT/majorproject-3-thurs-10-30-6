import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import './HomePage.css';

class HomePage extends Component {

    render() {
        if(this.props.loggedInStatus === "LOGGED_IN"){
            return (
                <div className='homepageStyle'>
                     <section className="Phrase">Find a Service that suits you</section>
                     <i className="Line"></i>
                </div>
             )
        }

        return (
           <div className='homepageStyle'>
                <section className="Phrase">Find a Service that suits you</section>
                <i className="Line"></i>
                <div className="Sign_Up_Button">
                    <Link to="/createAccount"><button className="Sign_Up">Sign Up</button></Link>
                </div>

           </div>
        )
    }
}
export default HomePage;
