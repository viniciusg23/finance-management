import React, { useState } from "react";
import { KeyboardAvoidingView, Platform, ScrollView, View } from "react-native";
import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import TextField from "shared/components/molecules/TextField";
import styles from "./styles";
import useProfile from "Features/AllProfilesPage/hooks/useProfiles";

export type TextFieldProps = {
	title: string;
	label: string;
	pholder: string;
	buttonPholder: string;
	setKeyboardVisible: (value: boolean) => void;
};

const BottomSheetAdd = ({
	title,
	label,
	pholder,
	buttonPholder,
	setKeyboardVisible
}: TextFieldProps) => {

	const [profileName, setProfileName] = useState("");
	const { createProfile } = useProfile();

	return (
		<KeyboardAvoidingView behavior={Platform.OS === "ios" ? "padding" : "height"} style={{flex: 1}}>
			<ScrollView>
				<View style={[styles.container]}>
					<View style={styles.titleContainer}>
						<MainText style={[styles.title]}>
							criar novo{" "}
							<MainText bold style={styles.title}>
								{title}
							</MainText>
						</MainText>
					</View>
					<View>
						<TextField
							label={label}
							pholder={pholder}
							style={{ marginBottom: 20 }}
							value={profileName}
							onChangeText={(value) => setProfileName(value)}
							onPress={() => setKeyboardVisible(true)}
						/>
						<MainButton
							disabled={profileName == ""}
							onPress={() => {
								createProfile(profileName);
								setProfileName("");
							}}
						>
							<MainText variant="body1" color={(profileName == "") ? 'white' : 'black'} bold>
								{buttonPholder}
							</MainText>
						</MainButton>
					</View>
				</View>
			</ScrollView>
		</KeyboardAvoidingView>
	);
};

export default BottomSheetAdd;
