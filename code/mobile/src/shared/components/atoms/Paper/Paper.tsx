import React, { PropsWithChildren } from "react";
import { ViewProps } from "react-native";
import { Section } from "./styles";

type PaperProps = ViewProps & {
	h?: number;
	w?: number;
	bgColor?: string;
	offShadow?: boolean;
	px?: string;
	py?: string;
	borderRadius?: number;
};

const Paper = ({
	w,
	h,
	bgColor,
	offShadow = true,
	px,
	py,
	borderRadius,
	children,
	...rest
}: PropsWithChildren<PaperProps>) => {	
	return (
		<Section
			h={h}
			w={w}
			bgColor={bgColor}
			offShadow={offShadow}
			px={px}
			py={py}
			borderRadius={borderRadius}
			{...rest}
		>
			{children}
		</Section>
	);
};

export default Paper;
