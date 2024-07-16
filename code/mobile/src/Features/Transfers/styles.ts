import { StyleSheet } from "react-native";
import colors from "../../shared/theme/colors";

const styles = StyleSheet.create({
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

export default styles;
