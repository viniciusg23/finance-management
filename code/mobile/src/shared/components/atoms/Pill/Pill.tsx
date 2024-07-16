import React, { PropsWithChildren } from "react";
import { ViewProps } from "react-native";
import { Section } from "./styles";

export type PillProps = ViewProps & {
	variant?: "small" | "medium" | "large";
	func?: () => void;
};

const Pill = ({
	func,
	variant,
	children,
	...rest
}: PropsWithChildren<PillProps>) => {
	return (
		<Section variant={variant} {...rest}>
			{children}
		</Section>
	);
};

export default Pill;
