import React, { Component } from 'react';
import './Style.css';
import Navbar from './Navbar'
import Translation from "./Translation"

const inFocusContainer = {
	opacity: '1',
  transition: 'all 1.5s',
	visibility: 'visible'
}

const outOfFocusContainer = {
	opacity: '0',
	transition: 'all 1.5s',
	visibility: 'hidden'
}

const submitButton = {
	display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between',
  alignItems: 'center',
  height: '70vh',
	marginTop: '30%'
}

export default class App extends Component {
	constructor(props) {
		super(props);

		this.state = {
			inputValue: "",
			apiResponse: {},
			needInput: true
		};

		this.updateInputValue = this.updateInputValue.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(event) {
		event.preventDefault();

		console.log("https://localhost:8080/translate?name=" + this.state.inputValue + "&lang=" + "en");

		fetch("/translate?name=" + this.state.inputValue + "&lang=" + "en").then(function (response) {
			return response.text();
		}).then((text) => {
			this.setState({apiResponse: JSON.parse(text)});
			this.setState({needInput: false});
			console.log(this.state.apiResponse);
		})
	}

	updateInputValue(event) {
		this.setState({inputValue: event.target.value});
	}

	render() {
		return (
			<div className="App">
				<Navbar/>
				<header className="App-header">

				<div style={submitButton}>
					<form onSubmit={this.handleSubmit}>
						<div style={this.state.needInput ? inFocusContainer : outOfFocusContainer}>
							<div className="name-input">
								<label>
									Name:
									<input type="text" value={this.state.inputValue} onChange={this.updateInputValue}/>
								</label>
							</div>
							<input type="submit" value="Submit"/>
						</div>
					</form>

					<div style={!(this.state.needInput) ? inFocusContainer : outOfFocusContainer}>
						<div classname="translations-list">
							<label>
								Translations:
								<Translation translations={this.state.apiResponse.translations}/>
							</label>
						</div>
					</div>
				</div>

				</header>
			</div>
		);
	}
}
