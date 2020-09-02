
import './Layout.css';
import { Link } from 'react-router-dom';
import React, { Component } from 'react'

class Header extends Component {
    toggleLogout = () => {
        this.props.toggleLogout();
    }
    render() {
        if(this.props.customer === true){
            return (
                <header>
                <h1 className='Logo'>AGMEServices</h1>
                <div className='top-menu'>
                    <Link to="/" style={linkStyle} >Home</Link>
                    <Link to="/aboutus" style={linkStyle} >About us</Link>
                    <Link to='/booking' style={linkStyle}>Make a Booking</Link>
                    <Link to='/customer' style={linkStyle}>Customer</Link>
                    <Link to='/' style={linkStyle}><button onClick={() => this.toggleLogout()} className="RegisterButton">Log Out</button></Link>
                </div>
            </header>
            )
        }else if(this.props.employee === true){
            return (
                <header>
                    <h1 className='Logo'>AGMEServices</h1>
                    <div className='top-menu'>
                        <Link to="/" style={linkStyle} >Home</Link>
                        <Link to="/abooutus" style={linkStyle} >About us</Link>
                        <Link to='/employee' style={linkStyle}>Employee</Link>
                        <Link to='/' style={linkStyle}><button onClick={this.props.toggleLogout.bind(this)} className="RegisterButton">Log Out</button></Link>

                    </div>
                </header>
            )
        }else{
            return (
                <header>
                    <h1 className='Logo'>AGMEServices</h1>
                    <div className='top-menu'>
                        <Link to="/" style={linkStyle} >Home</Link>
                        <Link to="/aboutus" style={linkStyle} >About us</Link>
                        <Link to='/login' style={linkStyle}>Login</Link>
                        <Link to='/staffLogin' style={linkStyle}>Staff Login</Link>
                        <Link to='/createAccount' style={linkStyle}><button className="RegisterButton">Register</button></Link>
                    </div>
            </header>
            )
        }
    }
}

const linkStyle = {
    paddingLeft: '10px',
    paddingRight: '10px',
    fontSize: '25px',
    textDecoration: 'none',
    color: 'white'
}

export default Header;
