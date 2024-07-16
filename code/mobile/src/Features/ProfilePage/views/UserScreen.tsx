import { SafeAreaView, Pressable } from "react-native";
import React from "react";
import {
	Container,
	InputsSection,
	LabelTitleSection,
	LogoutSection,
	MainContainer,
	TitleSection,
	additionalStyles,
} from "./styles/UserScreen.styles";
import { AntDesign } from "@expo/vector-icons";
import { UserScreenEnum } from "../utils/UserScreenEnum";
import TextField from "shared/components/molecules/TextField";
import MainText from "shared/components/atoms/MainText";
import Paper from "shared/components/atoms/Paper";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackNavigatorScreens } from "routes";
import { useUserStore } from "store/UserStore";
import { AuthService } from "services/AuthService";
import { useProfileStore } from "store/ProfileStore";

const UserScreen = () => {
	const navigation = useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const { userData, resetUserData } = useUserStore();
	const { resetProfileData } = useProfileStore();
	const onLogout = AuthService.onLogout

	const handleLogout = async () => {
		const result = await onLogout();
		if(result){
			resetUserData();
			resetProfileData();
			navigation.reset({
				index: 0,
				routes: [{ name: 'initial' }],
			});
		}
	}

	return (
		<SafeAreaView>
			<Container>
				<Paper style={{width: '90%'}}>
					<MainContainer>
						<TitleSection>
							<AntDesign name="user" size={36} color="black" />
							<MainText variant="xtitle" bold>
								{UserScreenEnum.title}
							</MainText>
						</TitleSection>
						<InputsSection>
							<LabelTitleSection>
								<MainText style={additionalStyles.sectionLabel}>
									{UserScreenEnum.personalInfoTitle}
								</MainText>
							</LabelTitleSection>
							<TextField
								label={UserScreenEnum.iptNameLabel}
								pholder=""
								style={additionalStyles.inputStyles}
								value={userData?.name || "-"}
							/>
							<TextField
								label={UserScreenEnum.iptNicknameLabel}
								pholder=""
								style={additionalStyles.inputStyles}
								value={userData?.nickname || "-"}
							/>
							<TextField
								label={UserScreenEnum.iptEmailLabel}
								pholder=""
								style={additionalStyles.inputStyles}
								value={userData?.email || "-"}
							/>
						</InputsSection>
						<LogoutSection>
							<Pressable onPress={handleLogout}>
								<MainText style={additionalStyles.labelError}>
									{UserScreenEnum.logoutText}
								</MainText>
							</Pressable>
						</LogoutSection>
					</MainContainer>
				</Paper>
			</Container>
		</SafeAreaView>
	);
};

export default UserScreen;
