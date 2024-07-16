import React from "react";
import { TextInputProps } from "react-native";
import MainText from "../../atoms/MainText";
import { InputText, Section } from "./styles";

export type TextFieldProps = TextInputProps & {
	label?: string;
	pholder: string;
	errorMessage?: string;
	rest?: any;
};

const TextField = ({
	label,
	pholder,
	errorMessage,
	...rest
}: TextFieldProps) => (
	<Section>
		{label && (
			<MainText
				variant="caption"
				style={{ marginLeft: 10, fontFamily: "Montserrat_500Medium" }}
			>
				{label}
			</MainText>
		)}

		<InputText placeholder={pholder} {...rest} />

		{errorMessage && (
			<MainText
				variant="caption"
				style={{
					marginLeft: 10,
					fontFamily: "Montserrat_500Medium",
					color: "",
				}}
			>
				{errorMessage}
			</MainText>
		)}
	</Section>
);

export default TextField;
