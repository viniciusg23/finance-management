import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

const createWalletStyles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: colors.background,
		padding: 10,
	},
	titleContainer: {
		paddingVertical: 10,
	},
	title: {
		fontSize: 20,
		marginBottom: 15,
	},
});

export default createWalletStyles;
