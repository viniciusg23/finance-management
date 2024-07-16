import React from "react";
import Paper from "shared/components/atoms/Paper";
import MainText from "shared/components/atoms/MainText";
import { Entypo } from "@expo/vector-icons";
import { TouchableOpacity } from "react-native-gesture-handler";

interface ProfileCardProps {
	handleOpenAction: () => void | undefined;
	profileId: string;
	name: string
}

export const ProfileCard = ({ handleOpenAction, profileId, name }: ProfileCardProps) => {
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
				<MainText
					variant="body2"
					bold
					style={{ fontSize: 16, padding: 6 }}
				>
					{name}
				</MainText>
				<Entypo name="chevron-small-right" size={24} color="black" />
			</Paper>
		</TouchableOpacity>
	);
};
