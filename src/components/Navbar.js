import React, { Component } from 'react';
import WycliffeLogo from '../img/Wycliffe_Logo.png';
class Navbar extends Component {

  render() {
    return (
      <div className="navbar" style={myStyles}> 
      <img src={WycliffeLogo} style={imgStyles}/>
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

const imgStyles = {
  height: "45px", 
  paddingTop: "20px",
  paddingLeft: "20px",
  position: "inline"
}

export default Navbar;

 
