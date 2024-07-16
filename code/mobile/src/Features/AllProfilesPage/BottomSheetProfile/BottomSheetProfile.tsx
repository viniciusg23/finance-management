import { Feather } from "@expo/vector-icons";
import React from "react";
import { View } from "react-native";
import { TouchableOpacity } from "react-native-gesture-handler";
import MainText from "shared/components/atoms/MainText";
import Paper from "shared/components/atoms/Paper";
import useProfile from "../hooks/useProfiles";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackNavigatorScreens } from "routes";
import { useProfileStore } from "store/ProfileStore";

interface BottomSheetProfileProps {
	profileId: string;
	name: string;
	onProfileDeleted: () => void;
}

export const BottomSheetProfile = ({ profileId, name, onProfileDeleted }: BottomSheetProfileProps) => {

	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const handleToHome = (profileId: string) => {
		navigation.navigate("application", { profileId: profileId });
	};
	const { setProfileId } = useProfileStore();
	const { deleteProfile } = useProfile();

	return (
		<View style={{ padding: 16 }}>
			<MainText variant="body1" bold style={{ marginBottom: 8 }}>
				opções
			</MainText>
			<TouchableOpacity onPress={() =>{
				setProfileId(profileId);
				handleToHome(profileId)
			}}>
				<Paper
					style={{
						display: "flex",
						flexDirection: "row",
						justifyContent: "space-between",
						alignItems: "center",
					}}
				>
					<MainText
						variant="body2"
						style={{
							fontSize: 16,
							padding: 6,
							color: "green",
							fontFamily: "Montserrat_400Regular",
						}}
					>
						selecionar perfil
					</MainText>
					<View
						style={{
							padding: 4,
							borderRadius: 6,
						}}
					>
						<Feather name="check" size={24} color="green" />
					</View>
				</Paper>
			</TouchableOpacity>
			<View
				style={{
					borderBottomWidth: 1.5,
					width: "80%",
					alignSelf: "center",
					borderColor: "#e9e9e9",
				}}
			></View>
			<TouchableOpacity onPress={() => deleteProfile(profileId, onProfileDeleted)}>
				<Paper
					style={{
						display: "flex",
						flexDirection: "row",
						justifyContent: "space-between",
					}}
				>
					<MainText
						variant="body2"
						style={{
							fontSize: 16,
							padding: 6,
							color: "red",
							fontFamily: "Montserrat_400Regular",
						}}
					>
						excluir perfil
					</MainText>
					<View
						style={{
							padding: 4,
						}}
					>
						<Feather name="trash-2" size={24} color="red" />
					</View>
				</Paper>
			</TouchableOpacity>
		</View>
	);
};
