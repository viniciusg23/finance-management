import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { FontAwesome } from "@expo/vector-icons";
import { SafeAreaView } from "react-native";
import { RootStackNavigatorScreens } from "routes";
import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import components from "shared/theme/components";
import {
	BtnContainer,
	Container,
	TitleContainer,
	aditionalStyles,
} from "./styles/InitialScreen.styles";
import { InitialScreenEnum } from "../utils/InitialScreenEnum";
import colors from "shared/theme/colors";

function InitialScreen() {
	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();

	return (
		<SafeAreaView
			style={[
				components.screen,
				{
					display: "flex",
					justifyContent: "center",
					alignItems: "center",
				},
			]}
		>
			<Container>
				<TitleContainer>
					<FontAwesome name="money" size={36} color="black" />
					<MainText
						variant="xtitle"
						bold
						style={{ textAlign: "center", marginBottom: 15 }}
					>
						FinApp
					</MainText>
				</TitleContainer>

				<BtnContainer>
					<MainButton
						style={aditionalStyles.btnStyle}
						onPress={() => navigation.navigate("login")}
					>
						<MainText
							variant="body1"
							style={{ color: colors.background }}
							bold
						>
							{InitialScreenEnum.loginBtn}
						</MainText>
					</MainButton>
					<MainButton
						style={aditionalStyles.btnStyle}
						onPress={() => navigation.navigate("register")}
					>
						<MainText
							variant="body1"
							style={{ color: colors.background }}
							bold
						>
							{InitialScreenEnum.registerBtn}
						</MainText>
					</MainButton>
				</BtnContainer>
			</Container>
		</SafeAreaView>
	);
}

export default InitialScreen;
