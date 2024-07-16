import { StyleSheet } from "react-native";
import styled from "styled-components/native";
import colors from "shared/theme/colors"

export const BottomSheetStyles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: colors.background,
        padding: 10
	},
    titleContainer: {
        paddingVertical: 10,
    },
	title: {
		fontSize: 20,
        marginBottom: 15
	},
});

export const Section = styled.View`
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	width: 100%;
`;