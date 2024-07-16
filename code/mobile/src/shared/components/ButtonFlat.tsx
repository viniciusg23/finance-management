import React from "react";
import {
	GestureResponderEvent,
	Pressable,
	Text
} from "react-native";
import Spacing from "shared/Spacing";
import Colors from "shared/theme/colors";
import FontSize from "shared/theme/fontSize";

type ButtonsProps = {
	label: string;
	onPress?: (event: GestureResponderEvent) => void;
	type: string;
	width?: number;
	disabled?: boolean;
};

const ButtonFlat = ({ label, onPress, type, width, disabled = false }: ButtonsProps) => {
	switch (type) {
		case "primary":
			return (
				<Pressable
					style={{
						padding: Spacing * 1.5,
						backgroundColor: disabled ? Colors.gray : Colors.primary,
						borderRadius: Spacing,
						width: width ?? 350,
						borderStyle: 'solid',
						borderColor: disabled ? Colors.darkGray : 'transparent'
					}}
					onPress={onPress}
				>
					<Text
						style={{
							color: disabled ? Colors.darkGray : Colors.onPrimary,
							textAlign: "center",
							fontSize: FontSize.large,
							fontWeight: "600",
						}}
					>
						{label}
					</Text>
				</Pressable>
			);
		case "secondary":
			return (
				<Pressable
					style={{
						padding: Spacing * 1.5,
						backgroundColor: "transparent",
					}}
					onPress={onPress}
				>
					<Text
						style={{
							color: Colors.primary,
							textAlign: "center",
							fontSize: FontSize.large,
							fontWeight: "600",
							width: width ?? 350,
						}}
					>
						{label}
					</Text>
				</Pressable>
			);
	}
};

export default ButtonFlat;
