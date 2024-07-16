import { StyleSheet } from "react-native";
import colors from "../../shared/theme/colors";
import styled from "styled-components/native";

export const WalletSection = styled.View`
	display: flex;
	flex-direction: column;
	width: 100%;
	row-gap: 20px;
	margin-top: 20px;
	margin-bottom: 20px;
`;

export const Section = styled.TouchableOpacity`
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
`;

export const SectionIcon = styled.View`
	display: flex;
	flex-direction: row;
	align-items: center;
	column-gap: 8px;
`;

export const Border = styled.View`
	border-width: 1px;
	border-color: #f1f2f3;
`;

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
