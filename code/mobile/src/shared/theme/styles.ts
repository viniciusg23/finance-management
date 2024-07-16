import { StyleSheet } from "react-native";
import colors from "./colors";

const styles = StyleSheet.create({
	backgroundBaseColor: {
		backgroundColor: colors.background,
	},
	containerBase: {
		flex: 1,
		height: "100%",
		justifyContent: "center",
		alignItems: "center",
	},
	homePageContainer: {
		flex: 1,
		height: "100%",
		backgroundColor: "#fff",
		alignItems: "center",
		justifyContent: "space-between",
	},
	textContainer: {
		flex: 1,
		justifyContent: "center",
		alignItems: "center",
	},
	buttonsContainer: {
		width: "100%",
		alignItems: "center",
		marginBottom: 20,
		borderRadius: 8,
	},
	buttonFlat: {
		width: 350,
		marginHorizontal: 50,
		marginVertical: 10,
	},
	ButtonColorPrimary: {
		backgroundColor: colors.primary,
	},
	buttonColorSecondary: {
		backgroundColor: "transparent",
	},
	inputBaseContainer: {
		height: 40,
		width: 350,
		borderColor: "transparent",
		borderRadius: 5,
		borderWidth: 1,
		marginBottom: 10,
		paddingLeft: 10,
		backgroundColor: colors.background,
	},
	label: {
		fontSize: 16,
		marginBottom: 5,
		marginTop: 10
	},
	labelError: {
		fontSize: 10,
		marginBottom: 10,
		fontWeight: 'bold',
		color: colors.error,
	},
	boxShadow: {
		shadowColor: colors.black,
		shadowOffset: {
			width: 0,
			height: 1,
		},
		shadowOpacity: 0.1,
		shadowRadius: 2.84,
		elevation: 3,
	},
});

export default styles;
