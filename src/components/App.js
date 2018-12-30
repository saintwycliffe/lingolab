import React, { Component } from 'react';

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

		console.log("/translate?name=\"" + this.state.inputValue + "\"&lang=\"" + "en" + "\"");

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
						<label>
							Name:
							<input type="text" value={this.state.inputValue} onChange={this.updateInputValue}/>
						</label>
						<input type="submit" value="Submit"/>
					</form>

				</header>
			</div>
		);
	}
}
