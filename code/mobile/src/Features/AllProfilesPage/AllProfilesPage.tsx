import { Entypo } from "@expo/vector-icons";
import BottomSheet, { BottomSheetBackdrop } from "@gorhom/bottom-sheet";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import BottomSheetAdd from "Features/BottomSheetAdd/BottomSheetAdd";
import { GetAllProfilesDTO } from "dto/user/GetAllProfilesDTO";
import React, { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { Keyboard, View } from "react-native";
import { TouchableOpacity } from "react-native-gesture-handler";
import { RootStackNavigatorScreens } from "routes";
import MainText from "shared/components/atoms/MainText";
import { ProfileCard } from "shared/components/organisms/ProfileCard/ProfileCard";
import { BottomSheetProfile } from "./BottomSheetProfile/BottomSheetProfile";
import { Header } from "./styles";
import useProfile from "./hooks/useProfiles";
import { useProfileStore } from "store/ProfileStore";

export const AllProfilesPage = () => {

	const bottomSheetRef = useRef<BottomSheet>(null);
	const bottomSheetAddRef = useRef<BottomSheet>(null);
	const [isKeyboardVisible, setKeyboardVisible] = useState(false);
	const snapMenu = useMemo(() => [isKeyboardVisible ? "50%" : "40%"], []);
	const handleCloseAction = () => bottomSheetRef.current?.close();
	const handleOpenAction = () => {
		bottomSheetRef.current?.expand();
	}
	const handleCloseAddAction = () => {
		bottomSheetAddRef.current?.close();
		setIsBottomSheetOpen(false);
		Keyboard.dismiss();
		setKeyboardVisible(false);
	};

	const handleOpenAddAction = () => {
		bottomSheetAddRef.current?.expand();
		setIsBottomSheetOpen(true);
	};

	const fetchProfiles = async () => {
		const { getAllProfiles } = useProfile();
		const profilesFetched = await getAllProfiles();
		setProfiles(profilesFetched);
	  }

	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const handleToHome = (profileId: string) => {
		navigation.navigate("application", { profileId: profileId });
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

	const [isBottomSheetOpen, setIsBottomSheetOpen] = useState(false);
	const [profiles, setProfiles] = useState<GetAllProfilesDTO | null>(null);
	const [profileId, setProfileId] = useState<string | null>(null);
	const [selectedProfile, setSelectedProfile] = useState<{ profileId: string, name: string } | null>(null);
	const [loading, setLoading] = useState(true);
	const profileState = useProfileStore();

	useEffect(() => {
		const loadProfileId = () => {
			const id = profileState.profileData.profileId;
			if (id)
				setProfileId(id);
		}

		loadProfileId();

		const fetchProfiles = async () => {
			const { getAllProfiles } = useProfile();
			const profilesFetched = await getAllProfiles();
			setProfiles(profilesFetched);
		}
		fetchProfiles();
	}, [])

	useEffect(() => {
		if (!isBottomSheetOpen) {
			const fetchProfiles = async () => {
				const { getAllProfiles } = useProfile();
				const profilesFetched = await getAllProfiles();
				setProfiles(profilesFetched);
			}
			fetchProfiles();
		}
	}, [isBottomSheetOpen]);


	const renderEmptyProfile = () => {
		return (
			<View style={{
				alignItems: "center",
				justifyContent: "center",
				padding: 32
			}}>
				<MainText variant="body2" style={{ marginBottom: 8, textAlign: 'center' }}>você não possui nenhum perfil dessa categoria.</MainText>
			</View>
		)
	}

	return (
		<>
			<View style={{ marginHorizontal: 15, marginTop: 50 }}>
				<Header>
					{profileId && (
						<TouchableOpacity
							onPress={() => handleToHome(profileId)}
							style={{
								backgroundColor: "#E7E8E9",
								borderRadius: 4,
							}}
						>
							<Entypo name="chevron-left" size={24} color="black" />
						</TouchableOpacity>
					)}
					<View
						style={{
							width: "90%",
							flexDirection: "row",
							justifyContent: "space-between",
						}}
					>
						<MainText variant="body1" bold>
							Perfis
						</MainText>
						<TouchableOpacity onPress={handleOpenAddAction}>
							<MainText
								variant="body2"
								bold
								style={{ alignSelf: "flex-end" }}
							>
								adicionar
							</MainText>
						</TouchableOpacity>
					</View>
				</Header>
				<View style={{ marginTop: 20 }}>
					<MainText variant="body1" bold>
						Meus Perfis
					</MainText>
					{profiles && profiles.ownm && profiles.ownm.map((profile) => (
						<ProfileCard
							key={profile.id}
							profileId={profile.id}
							name={profile.name}
							handleOpenAction={() => {
								setSelectedProfile({ profileId: profile.id, name: profile.name })
								handleOpenAction();
							}}
						/>
					))}
					{profiles?.ownm.length == 0 && renderEmptyProfile()}
				</View>
				<View style={{ marginTop: 8 }}>
					<MainText variant="body1" bold>
						Outros Perfis
					</MainText>
					{profiles && profiles.shared && profiles.shared.map((profile) => (
						<ProfileCard
							key={profile.id}
							profileId={profile.id}
							name={profile.name}
							handleOpenAction={() => {
								setSelectedProfile({ profileId: profile.id, name: profile.name })
								handleOpenAction();
							}}
						/>
					))}
					{profiles?.shared.length == 0 && renderEmptyProfile()}
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
				<BottomSheetProfile profileId={selectedProfile!?.profileId} name={selectedProfile!?.name} onProfileDeleted={fetchProfiles} />
			</BottomSheet>
			<BottomSheet
				ref={bottomSheetAddRef}
				index={-1}
				snapPoints={snapMenu}
				backgroundStyle={{ backgroundColor: "#fff" }}
				enablePanDownToClose
				onClose={handleCloseAddAction}
				backdropComponent={renderBackdrop}
			>
				<BottomSheetAdd
					title="perfil"
					label="Nome do perfil"
					pholder="Insira o nome do perfil"
					buttonPholder="criar perfil"
					setKeyboardVisible={setKeyboardVisible}
				/>
			</BottomSheet>
		</>
	);
};
export default AllProfilesPage;
