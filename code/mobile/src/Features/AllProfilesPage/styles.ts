import styled from "styled-components/native";
import { StyleSheet } from "react-native";

export const Header = styled.View`
	display: flex;
	flex-direction: row;
	align-items: center;
	column-gap: 8px;
`;

export const Container = styled.View`
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
`;

export const MainContainer = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
`;

export const InputContainer = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
	row-gap: 10px;
`;

export const LinkContainer = styled.View`
	display: flex;
	flex-direction: row;
	justify-content: center;
`;

export const aditionalStyles = StyleSheet.create({
	inputStyles: {
		width: 300,
		alignSelf: "center",
	},
	btnStyle: {
		width: 300,
		borderColor: "transparent",
		alignSelf: "center",
	},
	linkStyle: {},
});
