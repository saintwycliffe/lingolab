import React, {Component} from "react";

export default class App extends Component {

	render() {
		if (this.props.translations === undefined) {
			return null;
		}

		return (
			<div style={style}>
				{this.props.translations.map((translation) => {
					return (
						<span>
							{translation.translation}
							<br></br>
							<small>{translation.languageName}</small>
							<br></br>
						</span>
					);
				})}
			</div>
		);
	}
}

const style = {
		width: '50%'
};
