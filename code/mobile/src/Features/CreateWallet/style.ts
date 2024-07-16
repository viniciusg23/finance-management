import { StyleSheet } from "react-native";
import styled from "styled-components/native";
import colors from "../../shared/theme/colors";

const circleSize = 40;
const circleRingSize = 2;

const createWalletStyles = StyleSheet.create({
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
	group: {
		flexDirection: "row",
		justifyContent: "center",
		flexWrap: "wrap",
		marginBottom: 12,
	},
	circle: {
		width: circleSize + circleRingSize * 4,
		height: circleSize + circleRingSize * 4,
		borderRadius: 9999,
		backgroundColor: "white",
		borderWidth: circleRingSize,
		borderColor: "transparent",
		marginRight: 8,
		marginBottom: 12,
	},
	circleInside: {
		width: circleSize,
		height: circleSize,
		borderRadius: 9999,
		position: "absolute",
		top: circleRingSize,
		left: circleRingSize,
	},
});

export const Section = styled.View`
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	width: 100%;
`;

export default createWalletStyles;
