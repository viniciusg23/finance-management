import React, { useEffect, useState } from "react";
import { ScrollView, TouchableWithoutFeedback, View } from "react-native";
import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import TextField from "shared/components/molecules/TextField";
import styles, { Section } from "../style";
import { Formik } from "formik";
import { useProfileStore } from "store/ProfileStore";
import { WalletService } from "services/WalletService";
import { Wallet, useWalletStore } from "store/WalletStore";

const defaultColors = [
	"#6874e7",
	"#b8304f",
	"#758E4F",
	"#fa3741",
	"#F26419",
	"#F6AE2D",
	"#DFAEB4",
	"#7A93AC",
	"#33658A",
	"#3d2b56",
	"#42273B",
	"#171A21",
];

const CreateWallet = ({ myWallet }: { myWallet: Wallet | null }) => {
	const [value, setValue] = useState(0);
	const [colorSelected, setColorSelected] = useState("#6874e7");
	const [walletFunction, setWalletFunction] = useState("");
	const { profileData } = useProfileStore();
	const walletService = new WalletService();
	const { setWalletData, walletData } = useWalletStore();
	useEffect(() => {
		if (myWallet) {
			const indexColor = defaultColors.findIndex(
				(color) => myWallet.color === color
			);
			setValue(indexColor);
		}
	}, [myWallet]);

	// icon id = 46bf2572-7245-4ba3-add2-1ce212bd3071

	const handleSubmit = async ({
		name,
		description,
		walletId,
	}: {
		name: string;
		description: string;
		walletId?: string;
	}) => {
		const profileId = profileData.profileId;
		if (!profileId) return;
		const data = {
			iconId: "46bf2572-7245-4ba3-add2-1ce212bd3071",
			name: name,
			description: description,
			color: colorSelected,
			goalWallet: false,
		};
		switch (walletFunction) {
			case "edit": {
				let balance = walletData?.find(
					(wallet) => wallet.id === walletId
				)?.balance;

				if (!balance) balance = 0;
				if (!walletId) return console.log("WalletId not found");

				let dataChanged = { ...data, id: walletId, balance: balance };
				const response = await walletService.updateWallet(dataChanged);
				if (response.profile) {
					alert("Carteira editada com sucesso");
					if (walletData) {
						const walletSupport = walletData.map((wallet) => {
							if (wallet.id === response.id) return response;
							return wallet;
						});
						setWalletData(walletSupport);
					}
				}
				break;
			}
			case "delete": {
				if (!walletId) return console.log("WalletId not found delete");
				const response = await walletService.deleteWallet(walletId);
				if (response) {
					alert("Carteira deletada com sucesso");
					if (walletData) {
						const walletSupport = walletData.filter(
							(wallet) => wallet.id !== walletId
						);
						setWalletData(walletSupport);
					}
				}
				break;
			}
			case "create": {
				let dataChanged = { ...data, profileId: profileId };
				const response = await walletService.createWallet(dataChanged);
				if (response.profile) {
					alert("Carteira criada com sucesso");
					setWalletData(response);
				}
				break;
			}
		}
	};

	return (
		<ScrollView>
			<View style={[styles.container]}>
				<View style={styles.titleContainer}>
					<MainText style={[styles.title]}>
						{myWallet ? "editar" : "criar nova"}{" "}
						<MainText bold style={styles.title}>
							carteira
						</MainText>
					</MainText>
				</View>
				<Formik
					initialValues={{
						name: myWallet ? myWallet.name : "",
						description: myWallet ? myWallet.description : "",
						walletId: "",
					}}
					onSubmit={(values) => handleSubmit(values)}
					enableReinitialize
				>
					{({ handleChange, handleBlur, handleSubmit, values }) => (
						<View>
							<TextField
								label="Nome da carteira"
								pholder="Insira um nome para sua carteira"
								style={{ marginBottom: 20 }}
								onChangeText={handleChange("name")}
								onBlur={handleBlur("name")}
								value={values.name}
							/>
							<TextField
								label="Descrição"
								pholder="Dê uma descrição para a carteira"
								style={{ marginBottom: 20 }}
								onChangeText={handleChange("description")}
								onBlur={handleBlur("description")}
								value={values.description}
							/>
							<Section>
								<MainText
									variant="caption"
									style={{
										marginLeft: 10,
										marginBottom: 10,
										fontFamily: "Montserrat_500Medium",
									}}
								>
									Selecione uma cor para sua carteira
								</MainText>
								<View style={styles.group}>
									{defaultColors.map((item, index) => {
										const isActive = value === index;
										return (
											<View key={item}>
												<TouchableWithoutFeedback
													onPress={() => {
														setValue(index);
														setColorSelected(item);
													}}
												>
													<View
														style={[
															styles.circle,
															isActive && {
																borderColor:
																	item,
															},
														]}
													>
														<View
															style={[
																styles.circleInside,
																{
																	backgroundColor:
																		item,
																},
															]}
														/>
													</View>
												</TouchableWithoutFeedback>
											</View>
										);
									})}
								</View>
							</Section>
							{myWallet ? (
								<View
									style={{
										display: "flex",
										flexDirection: "row",
										justifyContent: "space-between",
									}}
								>
									<MainButton
										onPress={() => {
											(values.walletId = myWallet.id),
												setWalletFunction("edit"),
												handleSubmit();
										}}
										style={{ width: "48%" }}
									>
										<MainText variant="body1" bold>
											editar
										</MainText>
									</MainButton>
									<MainButton
										onPress={() => {
											(values.walletId = myWallet.id),
												setWalletFunction("delete"),
												handleSubmit();
										}}
										style={{
											width: "48%",
											borderColor: "red",
										}}
									>
										<MainText
											variant="body1"
											bold
											color="red"
										>
											excluir
										</MainText>
									</MainButton>
								</View>
							) : (
								<MainButton
									onPress={() => {
										setWalletFunction("create"),
											handleSubmit();
									}}
								>
									<MainText variant="body1" bold>
										adicionar
									</MainText>
								</MainButton>
							)}
						</View>
					)}
				</Formik>
			</View>
		</ScrollView>
	);
};

export default CreateWallet;
