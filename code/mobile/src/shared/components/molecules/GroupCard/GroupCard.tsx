import React, { PropsWithChildren } from "react";
import Paper from "../../atoms/Paper";
import { View, ViewProps } from "react-native";

export type GroupCardProps = ViewProps & {
	icon?: React.ReactNode;
	textTopLeft: string | React.ReactNode;
	textTopRight: string | React.ReactNode;
};

const GroupCard = ({
	icon,
	textTopLeft,
	textTopRight,
	children,
	...rest
}: PropsWithChildren<GroupCardProps>) => {
	return (
		<Paper {...rest}>
			<View
				style={{
					display: "flex",
					flexDirection: "row",
					justifyContent: "space-between",
					columnGap: 5,
					borderBottomWidth: 1,
					borderBottomColor: "#F1F2F3",
					margin: 5,
					paddingBottom: 10,
				}}
			>
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						columnGap: 5,
					}}
				>
					{icon && <View style={{ marginRight: 5 }}>{icon}</View>}
					{textTopLeft}
				</View>
				{textTopRight}
			</View>

			{children}
		</Paper>
	);
};

export default GroupCard;
