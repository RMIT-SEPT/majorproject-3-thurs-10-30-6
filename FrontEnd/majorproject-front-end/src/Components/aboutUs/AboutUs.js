import React from 'react'
import { Link } from 'react-router-dom';

function AboutUs() {
    return (
        <div>
            <h1 className="about_title">About AGME</h1>
            <p className="about_text">Welcome to AGME services. We take pride and enjoyment in providing services for the general public.
                                    We are a company that has been around for the last 50 years and have been growing ever since. No matter
                                    what service you need we able to provide. New customer are always welcome just simply sign up and start booking.
                                    We offer a range of packages for new providers that want to be apart of our vast network, Simply scroll to the
                                    bottom of the page and grab our constact details to get in touch.</p>

                                    <Link to="/createAccount"><button className="about_sign_up">Sign Up Now</button></Link>
        </div>
    )
}

export default AboutUs;
