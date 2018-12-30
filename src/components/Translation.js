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
						<div>
							<strong>{translation.translation.substring(1, translation.translation.length - 2)}</strong>
							<i>{translation.language.substring(1, translation.translation.length - 2)}</i>
						</div>
					);
				})}
			</div>
		);
	}
}

const style = {
		border: '4px solid'
};