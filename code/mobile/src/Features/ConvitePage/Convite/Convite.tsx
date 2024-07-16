import React from "react";
import Paper from "shared/components/atoms/Paper";
import MainText from "shared/components/atoms/MainText";
import { View } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import { TouchableOpacity } from "react-native-gesture-handler";

export const Convite = () => {
	return (
		<View>
			<Paper
				style={{
					display: "flex",
					flexDirection: "row",
					justifyContent: "space-between",
					alignItems: "center",
				}}
				py="16"
			>
				<MainText
					variant="body2"
					bold
					style={{ fontSize: 16, padding: 6 }}
				>
					Convite de vinicius1
				</MainText>
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						justifyContent: "space-between",
						alignItems: "center",
						width: 100,
					}}
				>
					<TouchableOpacity
						style={{
							backgroundColor: "#ffe5e5",
							padding: 6,
							borderRadius: 10,
						}}
					>
						<AntDesign name="close" size={24} color="red" />
					</TouchableOpacity>
					<TouchableOpacity
						style={{
							backgroundColor: "#e5f2e5",
							padding: 6,
							borderRadius: 10,
						}}
					>
						<AntDesign name="check" size={24} color="green" />
					</TouchableOpacity>
				</View>
			</Paper>
		</View>
	);
};
