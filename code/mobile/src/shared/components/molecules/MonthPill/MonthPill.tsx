import { TouchableOpacity, View } from "react-native";
import React from "react";
import Pill from "shared/components/atoms/Pill";
import MainText from "shared/components/atoms/MainText";
import { AntDesign } from "@expo/vector-icons";

interface MonthPillProps {
	setMonth: React.Dispatch<React.SetStateAction<number>>;
	curMonth: number;
	month: string[];
}

const MonthPill = ({ setMonth, curMonth, month }: MonthPillProps) => {
	return (
		<Pill style={{ marginBottom: 25 }}>
			<View
				style={{
					display: "flex",
					flexDirection: "row",
					justifyContent: "space-between",
					alignItems: "center",
				}}
			>
				<TouchableOpacity
					onPress={() =>
						setMonth(
							(curMonth - 1) % 12 === -1
								? 11
								: (curMonth - 1) % 12
						)
					}
				>
					<AntDesign name="leftcircleo" size={20} color="black" />
				</TouchableOpacity>
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						alignItems: "baseline",
						justifyContent: "space-between",
						width: "60%",
					}}
				>
					<MainText>
						{month[curMonth - 1 === -1 ? 11 : curMonth - 1]}
					</MainText>
					<MainText variant="body1" bold>
						{month[curMonth]}
					</MainText>
					<MainText>
						{month[curMonth + 1 === 12 ? 0 : curMonth + 1]}
					</MainText>
				</View>
				<TouchableOpacity
					onPress={() =>
						setMonth(
							(curMonth + 1) % 12 === 0 ? 0 : (curMonth + 1) % 12
						)
					}
				>
					<AntDesign name="rightcircleo" size={20} color="black" />
				</TouchableOpacity>
			</View>
		</Pill>
	);
};

export default MonthPill;
