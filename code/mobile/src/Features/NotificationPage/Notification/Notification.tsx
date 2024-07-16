import React from "react";
import Paper from "shared/components/atoms/Paper";
import MainText from "shared/components/atoms/MainText";
import { Entypo } from "@expo/vector-icons";
import { TouchableOpacity } from "react-native-gesture-handler";
import { View } from "react-native";

interface NotificationProps {
	handleOpenAction: () => void | undefined;
}

export const Notification = ({ handleOpenAction }: NotificationProps) => {
	return (
		<TouchableOpacity onPress={handleOpenAction}>
			<Paper
				style={{
					display: "flex",
					flexDirection: "row",
					justifyContent: "space-between",
					alignItems: "center",
				}}
			>
				<View>
					<MainText
						variant="body2"
						bold
						style={{ fontSize: 16, padding: 6 }}
					>
						Notification
					</MainText>
					<MainText
						variant="input"
						style={{
							padding: 6,
							paddingTop: 2,
							width: 300,
						}}
            numberOfLines={2}
					>
						Notification, teste aleatório escrevendo qualquer coisa
						pra ver o que vai dar disso até o momento da necessidade
						dele quebrar
					</MainText>
				</View>
				<Entypo name="chevron-small-right" size={24} color="black" />
			</Paper>
		</TouchableOpacity>
	);
};
