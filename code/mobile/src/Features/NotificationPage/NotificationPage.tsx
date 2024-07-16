import { View } from "react-native";
import React, { useCallback, useMemo, useRef } from "react";
import { TouchableOpacity } from "react-native-gesture-handler";
import { Entypo } from "@expo/vector-icons";
import { Header } from "./styles";
import MainText from "shared/components/atoms/MainText";
import BottomSheet, { BottomSheetBackdrop } from "@gorhom/bottom-sheet";
import BottomSheetAdd from "Features/BottomSheetAdd/BottomSheetAdd";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackNavigatorScreens } from "routes";
import { Notification } from "./Notification/Notification";
import NotificationBotton from "./NotificationBotton/NotificationBotton";

export const NotificationPage = () => {
	const bottomSheetRef = useRef<BottomSheet>(null);
	const snapMenu = useMemo(() => ["40%"], []);
	const handleCloseAction = () => bottomSheetRef.current?.close();
	const handleOpenAction = () => bottomSheetRef.current?.expand();

	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const handleToHome = () => {
		navigation.navigate("application");
	};

	const renderBackdrop = useCallback(
		(props: any) => (
			<BottomSheetBackdrop
				{...props}
				appearsOnIndex={0}
				disappearsOnIndex={-1}
				onPress={handleCloseAction}
			/>
		),
		[]
	);
	return (
		<>
			<View style={{ marginHorizontal: 15, marginTop: 50 }}>
				<Header>
					<TouchableOpacity
						onPress={handleToHome}
						style={{
							backgroundColor: "#E7E8E9",
							borderRadius: 4,
						}}
					>
						<Entypo name="chevron-left" size={24} color="black" />
					</TouchableOpacity>
					<View
						style={{
							width: "90%",
							flexDirection: "row",
							justifyContent: "space-between",
						}}
					>
						<MainText variant="body1" bold>
							Notificações
						</MainText>
					</View>
				</Header>
				<View style={{ marginTop: 16 }}>
					<Notification handleOpenAction={handleOpenAction} />
				</View>
			</View>

			<BottomSheet
				ref={bottomSheetRef}
				index={-1}
				snapPoints={snapMenu}
				backgroundStyle={{ backgroundColor: "#fff" }}
				enablePanDownToClose
				onClose={handleCloseAction}
				backdropComponent={renderBackdrop}
			>
				<NotificationBotton />
			</BottomSheet>
		</>
	);
};

export default NotificationPage;
