import { FontAwesome } from "@expo/vector-icons";
import { StackNavigationProp } from "@react-navigation/stack";
import React, { useState } from "react";
import { Pressable, SafeAreaView } from "react-native";
import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import TextField from "shared/components/molecules/TextField";
import colors from "shared/theme/colors";
import { LoginTextsEnum } from "../utils/LoginTextsEnum";
import {
	Container,
	InputContainer,
	LinkContainer,
	MainContainer,
	TitleSection,
	aditionalStyles,
} from "./styles/Login.styled";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { useUserStore } from "store/UserStore";
import { RootStackNavigatorScreens } from "routes";
import AuthService from "services/AuthService";
import { useProfileStore } from "store/ProfileStore";
import { Storage } from "shared/utils/Storage";

interface LoginProps {
	navigation: StackNavigationProp<any>;
}

function Login() {
	const navigation = useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const { setUserData } = useUserStore();
	const { profileData } = useProfileStore();

	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	async function handleLogin() {
		const result = await AuthService.onLogin({ email, password });
		if(result instanceof Object) {
			Storage.saveUserToken(result.token);
			setUserData({ ...result, email: email });
			if (profileData?.profileId) {
				navigation.navigate("application", { profileId: profileData.profileId });
			  } else {
				navigation.navigate("allProfiles");
			  }
			return;
		}
		return;
	};

	const handleToRegister = () => {
		navigation.navigate("register");
	}

	return (
		<SafeAreaView
			style={{backgroundColor: colors.background}}
		>
			<Container>
				<MainContainer>
					<TitleSection>
						<FontAwesome name="money" size={36} color="black" />
						<MainText variant="xtitle">{LoginTextsEnum.title}</MainText>
					</TitleSection>
					<InputContainer>
						<TextField
							label=""
							pholder={LoginTextsEnum.inputLogin}
							style={aditionalStyles.inputStyles}
							value={email}
							onChangeText={(text: string) => setEmail(text)}
						/>
						<TextField
							label=""
							pholder={LoginTextsEnum.inputPassword}
							style={aditionalStyles.inputStyles}
							value={password}
							onChangeText={(text: string) => setPassword(text)}
							secureTextEntry
						/>
						<MainButton style={aditionalStyles.btnStyle} onPress={handleLogin}>
							<MainText
								variant="body1"
								style={{ color: colors.background }}
								bold
							>
								{LoginTextsEnum.btnLabel}
							</MainText>
						</MainButton>
						<LinkContainer>
							<MainText variant="body1">
								{LoginTextsEnum.auxLinkLabel}
							</MainText>
							<Pressable onPress={handleToRegister}>
								<MainText
									variant="body1"
									bold
									style={aditionalStyles.linkStyle}
								>
									{LoginTextsEnum.linkLabel}
								</MainText>
							</Pressable>
						</LinkContainer>
					</InputContainer>
				</MainContainer>
			</Container>
		</SafeAreaView>
	);
};

export default Login;
