import React, { PropsWithChildren } from "react";
import { TouchableOpacityProps } from "react-native";
import { Section } from "./styles";
import theme from "styles/theme";
import { Theme } from "styles/styled";
import MainText from "../MainText";

export type MainButtonProps = TouchableOpacityProps & {
	onPress?: () => void;
	text?: string;
	textColor?: keyof Theme["colors"];
	variant?: keyof Theme["textVariantes"];
};

const MainButton = ({
	onPress,
	text,
	variant = "body1",
	textColor = 'black',
	children,
	...rest
}: PropsWithChildren<MainButtonProps>) => {
	return (
		<Section onPress={onPress} style={rest.disabled && {backgroundColor: theme.colors.grey, borderColor: theme.colors.grey }} {...rest}>
			{text ? <MainText bold variant={variant} color={rest.disabled ? 'white' : textColor}>{text}</MainText> : children}
		</Section>
	);
};

export default MainButton;
