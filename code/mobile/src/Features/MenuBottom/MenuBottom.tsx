import React from "react";
import { View } from "react-native";
import { Ionicons, FontAwesome, SimpleLineIcons } from "@expo/vector-icons";
import MainText from "shared/components/atoms/MainText";
import styles from "./styles";
import { TouchableOpacity } from "react-native-gesture-handler";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackNavigatorScreens } from "routes";

const MenuBottom = () => {
	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const handleToAllProfiles = () => {
		navigation.navigate("allProfiles");
	};
	const handleToConvitePage = () => {
		navigation.navigate("convitePage");
	};

	return (
		<View style={[styles.container]}>
			<View style={styles.titleContainer}>
				<MainText bold style={styles.title}>
					menu
				</MainText>
			</View>
			<View style={styles.buttons}>
				<TouchableOpacity
					style={styles.profile}
					onPress={handleToAllProfiles}
				>
					<View
						style={{
							display: "flex",
							flexDirection: "column",
							alignItems: "center",
						}}
					>
						<Ionicons
							name="person-outline"
							size={28}
							color="black"
						/>
						<MainText
							variant="body2"
							bold
							style={{ marginTop: 22 }}
						>
							perfis
						</MainText>
					</View>
				</TouchableOpacity>
				<TouchableOpacity
					style={styles.profile}
					onPress={handleToConvitePage}
				>
					<View
						style={{
							display: "flex",
							flexDirection: "column",
							alignItems: "center",
						}}
					>
						<View>
							<FontAwesome
								name="envelope-o"
								size={28}
								color="black"
							/>
						</View>
						<MainText
							variant="body2"
							bold
							style={{ marginTop: 22 }}
						>
							convites
						</MainText>
					</View>
				</TouchableOpacity>
			</View>
		</View>
	);
};

export default MenuBottom;
