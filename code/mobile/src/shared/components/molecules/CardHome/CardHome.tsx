import React, { PropsWithChildren } from "react";
import { Section, Icon, Text } from "./styles";
import Paper from "../../atoms/Paper";
import MainText from "../../atoms/MainText";
import { ViewProps } from "react-native";

export type CardHomeProps = ViewProps & {
	icon: React.ReactNode;
	text: string;
	caption?: string;
	bgIcon?: string;
};

const CardHome = ({
	icon,
	text,
	caption,
	bgIcon,
	...rest
}: PropsWithChildren<CardHomeProps>) => {
	return (
		<Paper {...rest}>
			<Section>
				<Icon bgIcon={bgIcon}>{icon}</Icon>
				<Text>
					{caption && (
						<MainText variant="caption">{caption}</MainText>
					)}
					<MainText variant="body2" bold>
						{text}
					</MainText>
				</Text>
			</Section>
		</Paper>
	);
};

export default CardHome;
