import {
	Feather,
	FontAwesome6,
	MaterialCommunityIcons,
	SimpleLineIcons,
} from "@expo/vector-icons";
import BottomSheet, { BottomSheetBackdrop } from "@gorhom/bottom-sheet";
import { TouchableOpacity } from "react-native-gesture-handler";
import React, { useCallback, useEffect, useMemo, useRef } from "react";
import { ScrollView, View } from "react-native";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import CreateWallet from "../../CreateWallet/views/CreateWallet";
import styles, { Border, Section, SectionIcon, WalletSection } from "../styles";
import MainText from "shared/components/atoms/MainText";
import CardHome from "shared/components/molecules/CardHome";
import GroupCard from "shared/components/molecules/GroupCard";
import MainButton from "shared/components/atoms/MainButton";
import MenuBottom from "Features/MenuBottom/MenuBottom";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackNavigatorScreens } from "routes";
import { useUserStore } from "store/UserStore";
import { ProfileService } from "services/ProfileService";
import { Wallet, useWalletStore } from "store/WalletStore";
import { useProfileStore } from "store/ProfileStore";

export const HomePage = () => {
	const bottomSheetRef = useRef<BottomSheet>(null);
	const bottomSheetAddCardRef = useRef<BottomSheet>(null);
	const snapPoints = useMemo(() => ["75%"], []);
	const snapMenu = useMemo(() => ["40%"], []);
	const { userData } = useUserStore();
	const profileService = new ProfileService();
	const { setWalletData, walletData } = useWalletStore();
	const { profileData } = useProfileStore();
	const [myWallet, setMyWallet] = React.useState<Wallet | null>(null);
	const month = [
		"janeiro",
		"fevereiro",
		"março",
		"abril",
		"maio",
		"junho",
		"julho",
		"agosto",
		"setembro",
		"outubro",
		"novembro",
		"dezembro",
	];

	const handleCloseAction = () => {
		bottomSheetRef.current?.close();
	};
	const handleOpenAction = (ref: any) => {
		ref.current?.expand();
		setMyWallet(null);
	};
	const handleOpenWalletBottom = (ref: any, wallet: Wallet) => {
		ref.current?.expand();
		setMyWallet(wallet);
	};

	const navigation =
		useNavigation<NativeStackNavigationProp<RootStackNavigatorScreens>>();
	const handleToNotifications = () => navigation.navigate("notificationPage");

	useEffect(() => {
		const fetchData = async () => {
			if (profileData.profileId) {
				const result = await profileService.getAllProfileWallets(
					profileData.profileId
				);
				if (result) setWalletData(result.wallets);
			}
		};
		fetchData();
	}, []);

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

	const totalValue = walletData?.reduce((sum, wallet) => {
		return sum + (wallet.balance || 0);
	}, 0);

	return (
		<GestureHandlerRootView style={{ flex: 1 }}>
			<View style={[styles.homePageContainer]}>
				<ScrollView>
					<View style={{ marginHorizontal: 15 }}>
						<View style={styles.header}>
							<View
								style={{
									flexDirection: "row",
									alignItems: "center",
									columnGap: 8,
								}}
							>
								<TouchableOpacity
									onPress={() =>
										handleOpenAction(bottomSheetRef)
									}
								>
									<Feather
										name="menu"
										size={24}
										color="black"
									/>
								</TouchableOpacity>
								<View>
									<MainText>olá,</MainText>
									<MainText variant="body2" bold>
										{userData?.name ?? "Augusto"}
									</MainText>
								</View>
							</View>
							<TouchableOpacity onPress={handleToNotifications}>
								<MaterialCommunityIcons
									name="bell-outline"
									size={24}
									color="black"
								/>
							</TouchableOpacity>
						</View>
						<View style={styles.flexPaper}>
							<View style={{ flexDirection: "row" }}>
								<MainText>resumo de </MainText>
								<MainText variant="body2" bold>
									{month[new Date().getMonth()]}
								</MainText>
							</View>
							<CardHome
								icon={
									<MaterialCommunityIcons
										name="piggy-bank-outline"
										size={24}
										color="gray"
									/>
								}
								text={
									totalValue
										? `R$ ${totalValue.toFixed(2)}`
										: "R$ 0,00"
								}
								caption="Saldo atual"
								bgIcon="#DFDFDF"
								style={{
									flexShrink: 0,
									flexGrow: 4,
									minWidth: 300,
								}}
							/>
							<CardHome
								icon={
									<FontAwesome6
										name="dollar"
										size={24}
										color="green"
									/>
								}
								text="R$ 2.000,00"
								caption="Receitas"
								bgIcon="#f0f8e7"
								style={{
									flexShrink: 1,
									flexGrow: 1,
								}}
							/>
							<CardHome
								icon={
									<FontAwesome6
										name="dollar"
										size={24}
										color="red"
									/>
								}
								text="R$ 3.000,00"
								caption="Despesas"
								bgIcon="#ffede7"
								style={{
									flexShrink: 1,
									flexGrow: 1,
								}}
							/>
						</View>
						<GroupCard
							icon={
								<SimpleLineIcons
									name="wallet"
									size={16}
									color="black"
								/>
							}
							textTopLeft={
								<>
									<MainText variant="body2">minhas</MainText>
									<MainText variant="body2" bold>
										carteiras
									</MainText>
								</>
							}
							textTopRight={<MainText variant="body2"></MainText>}
						>
							{!walletData ? (
								<View
									style={{
										display: "flex",
										flexDirection: "column",
										alignItems: "center",
										width: "100%",
										rowGap: 20,
										marginVertical: 20,
									}}
								>
									<MainText variant="input">
										você não tem nenhuma carteira cadastrada
									</MainText>
								</View>
							) : (
								<WalletSection>
									{walletData.map((wallet, key) => (
										<>
											<Section
												key={key}
												onPress={() =>
													handleOpenWalletBottom(
														bottomSheetAddCardRef,
														wallet
													)
												}
											>
												<SectionIcon>
													<View
														style={{
															backgroundColor:
																wallet.color,
															padding: 8,
															borderRadius: 10,
														}}
													>
														<MaterialCommunityIcons
															name="wallet"
															size={24}
															color="white"
														/>
													</View>
													<MainText variant="body2">
														{wallet.name}
													</MainText>
												</SectionIcon>

												<MainText variant="body2" bold>
													R${" "}
													{wallet.balance?.toFixed(2)}
												</MainText>
											</Section>
											{walletData.length - 1 != key && (
												<Border />
											)}
										</>
									))}
								</WalletSection>
							)}
							<MainButton
								onPress={() =>
									handleOpenAction(bottomSheetAddCardRef)
								}
							>
								<MainText variant="body1" bold>
									adicionar
								</MainText>
							</MainButton>
						</GroupCard>
					</View>
				</ScrollView>
			</View>
			<BottomSheet
				ref={bottomSheetAddCardRef}
				index={-1}
				snapPoints={snapPoints}
				backgroundStyle={{ backgroundColor: "#fff" }}
				enablePanDownToClose
				onClose={handleCloseAction}
				backdropComponent={renderBackdrop}
			>
				<CreateWallet myWallet={myWallet} />
			</BottomSheet>
			<BottomSheet
				ref={bottomSheetRef}
				index={-1}
				snapPoints={snapMenu}
				backgroundStyle={{ backgroundColor: "#fff" }}
				enablePanDownToClose
				onClose={handleCloseAction}
				backdropComponent={renderBackdrop}
			>
				<MenuBottom />
			</BottomSheet>
		</GestureHandlerRootView>
	);
};
