import React, { PropsWithChildren } from "react";
import { TextProps } from "react-native";
import { Section } from "./styles";
import { Theme } from "styles/styled";
import theme from "styles/theme";

export type MainTextProps = TextProps & {
	variant?: keyof Theme["textVariantes"];
	color?: keyof Theme["colors"];
	bold?: boolean;
};

const MainText = ({
	variant,
	color,
	bold,
	children,
	...rest
}: PropsWithChildren<MainTextProps>) => {
	return (
		<Section variant={variant} color={color} bold={bold} {...rest}>
			{children}
		</Section>
	);
};

export default MainText;
