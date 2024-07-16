import React from "react";
import { Entypo } from "@expo/vector-icons";
import Paper from "shared/components/atoms/Paper";
import MainText from "shared/components/atoms/MainText";
import { View } from "react-native";

interface CategoryCardProps {
	name: string;
}

export const CategoryCard = ({ name }: CategoryCardProps) => {
	return (
		<View>
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
		</View>
	);
};
