import React, { Component } from 'react';

class Navbar extends Component {

  render() {
    return (
      <div className="navbar" style={myStyles}>
      <ul style={ulStyles}>
        <li style={navStyles}><a href="https://www.wycliffe.org/get-involved" style={aStyles}>Get Involved</a></li>  
        <li style={navStyles}><a href="https://www.wycliffe.org/blog" style={aStyles}>Blog</a></li>
        <li style={navStyles}><a href="https://www.wycliffe.org/about" style={aStyles}>About</a></li>
        <li style={navStyles}><a href="https://www.wycliffe.org/resources" style={aStyles}>Resources</a></li>
        <li style={navStyles}><a href="https://www.wycliffe.org/missionaries" style={aStyles}>Missionaries</a></li>
        <li style={navStyles}><a href="https://www.wycliffe.org/donate" style={aStyles}>Donate</a></li>           
      </ul>
      </div>
    );
  }
}

const myStyles = {
  top: 0, 
  position: 'fixed', 
  height: 80, 
  width: '100%',
  backgroundColor: 'rgba(36, 36, 36, 0.95)',
  opacity: 1
};

const navStyles = {
  display: "inline",
 }

 const ulStyles = {
  display: "flex",
  justifyContent: "space-around"
 }

 const aStyles = {
  textDecoration: "none",
  color: "white",
  fontSize: "0.85em",
  lineHeight: "2.5em"
  //fontFamily: "Gotham SSm A", "Gotham SSm B", "Verdana", "sans-serif";
 }


export default Navbar;

 
