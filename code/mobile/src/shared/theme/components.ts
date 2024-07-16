import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

const components = StyleSheet.create({
	screen: {
		flex: 1,
		backgroundColor: colors.background,
		width: "100%"
	},


	containerSmall: {
		width: "40%",
	},
	containerMedium: {
		width: "60%",
	},
	container: {
		width: "80%",
	},
	containerLarge: {
		width: "90%",
	},
	

	button: {
		width: "100%",
		color: colors.onBackground,
		padding: 16,
		
		borderBlockColor: colors.onBackground,
		borderRadius: 30,
	},
	buttonPrimary: {
		width: "100%",
		backgroundColor: colors.primary,
		color: colors.onPrimary,
		padding: 16,

		borderBlockColor: colors.primary,
		borderRadius: 30,
	},
	buttonSecondary: {
		width: "100%",
		backgroundColor: colors.secondary,
		color: colors.onSecondary,
		padding: 16,

		borderBlockColor: colors.secondary,
		borderRadius: 30,
	},

	columnItensContainer: {
		width: "100%",
		display: "flex",
		flexDirection: "column",
		alignItems: "center",
		gap: 10,
	},


	//----------------------------------------
	homePageContainer: {
		flex: 1,
		backgroundColor: colors.backgroundColor,
	},
	flexContainer: {
		display: "flex",
		flexDirection: "row",
		flexWrap: "wrap",
		alignItems: "center",
		justifyContent: "space-between",
		backgroundColor: "#fff",
		rowGap: 10,
	},
	flexPaper: {
		display: "flex",
		flexDirection: "row",
		backgroundColor: colors.backgroundColor,
		justifyContent: "space-between",
		flexWrap: "wrap",
		columnGap: 15,
	},
	header: {
		display: "flex",
		flexDirection: "row",
		justifyContent: "space-between",
		alignItems: "center",
		marginTop: 60,
		marginBottom: 20,
	},
});

export default components;