import React from "react";
import { StyleSheet } from "react-native";
import { Dropdown } from "react-native-element-dropdown";
import MainText from "shared/components/atoms/MainText";
import { Section } from "./styles";

interface SelectionProps {
	label?: string;
	pholder: string;
	dataList: { label: string; value: string }[];
	setValue: (value: string) => void;
	value: string;
}

const Selection = ({
	label,
	pholder,
	dataList,
	setValue,
	value,
}: SelectionProps) => {
	return (
		<Section>
			<MainText
				variant="caption"
				style={{ marginLeft: 10, fontFamily: "Montserrat_500Medium" }}
			>
				{label}
			</MainText>
			<Dropdown
				style={styles.dropdown}
				containerStyle={{
					width: "80%",
					alignSelf: "center",
					marginRight: 15,
					marginTop: -4,
					borderBottomRightRadius: 30,
					borderBottomStartRadius: 30,
					borderWidth: 1,
					borderColor: "black",
					borderTopWidth: 0,
				}}
				placeholderStyle={{
					color: "#919191",
					fontFamily: "Montserrat_400Regular",
					fontSize: 14,
				}}
				data={dataList}
				maxHeight={300}
				labelField="label"
				valueField="value"
				placeholder={pholder}
				searchPlaceholder="Search..."
				value={value}
				onChange={(item: { value: string }) => {
					setValue(item.value);
				}}
			/>
		</Section>
	);
};

export default Selection;

const styles = StyleSheet.create({
	dropdown: {
		borderWidth: 1,
		borderRadius: 50,
		borderColor: "black",
		paddingHorizontal: 16,
		paddingVertical: 10,
		width: "100%",
		fontSize: 14,
		backgroundColor: "white",
	},
});
