import { StyleSheet } from "react-native";
import colors from "../../shared/theme/colors";

const createWalletStyles = StyleSheet.create({
	container: {
		backgroundColor: colors.background,
		padding: 15,
		paddingTop: 0,
	},
	titleContainer: {
		paddingVertical: 5,
	},
	title: {
		fontSize: 20,
		marginBottom: 10,
	},
	buttons: {
		display: "flex",
		flexDirection: "row",
		justifyContent: "space-around",
		marginTop: 20,
	},
	profile: {
		width: 150,
		height: 140,
		padding: 20,
		borderRadius: 15,
		borderWidth: 2,
		borderColor: "#E7E8E9",
		display: "flex",
		justifyContent: "flex-end",
	},
});

export default createWalletStyles;
