import React, { Component } from 'react';
import './Style.css';
import Translation from "./Translation"

export default class App extends Component {
	constructor(props) {
		super(props);

		this.state = {
			inputValue: "",
			apiResponse: {}
		};

		this.updateInputValue = this.updateInputValue.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(event) {
		alert('A name was submitted: ' + this.state.inputValue);

		event.preventDefault();

		console.log("https://localhost:8080/translate?name=\"" + this.state.inputValue + "\"&lang=\"" + "en" + "\"");

		fetch("/translate?name=\"" + this.state.inputValue + "\"&lang=\"" + "en" + "\"").then(function (response) {
			return response.text();
		}).then((text) => {
			this.setState({apiResponse: JSON.parse(text)});
			console.log(this.state.apiResponse);
		})
	}

	updateInputValue(event) {
		this.setState({inputValue: event.target.value});
	}

	render() {
		return (
			<div className="App">
				<header className="App-header">

					<form onSubmit={this.handleSubmit}>
						<div ClassName="submitContainer" style={{
							display: 'flex',
							flexDirection: 'column',
							justifyContent: 'space-between',
							alignItems: 'center',
							height: '90vh'
						}}>
						<div Classname="header"><h1>Wycliffe</h1></div>
						<div Classname="name-input">
							<label>
								Name:
								<input type="text" value={this.state.inputValue} onChange={this.updateInputValue}/>
							</label>
						</div>
						<input type="submit" value="Submit"/>
						</div>
					</form>

					<Translation translations={this.state.apiResponse.translations}/>

				</header>
			</div>
		);
	}
}
