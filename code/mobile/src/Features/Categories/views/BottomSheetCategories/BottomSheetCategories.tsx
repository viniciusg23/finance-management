import { View, Text, ScrollView, TouchableWithoutFeedback } from "react-native";
import React, { useState } from "react";

import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import TextField from "shared/components/molecules/TextField";
import { BottomSheetStyles, Section } from "./BottomSheetCategories.styled";
import Selection from "shared/components/molecules/Selection/Selection";
import { Category, useCategoryStore } from "store/CategoryStore";
import { useProfileStore } from "store/ProfileStore";
import { Formik } from "formik";
import { CategoryService } from "services/CategoryService";

const TYPE_LIST = [
	{ label: "Receita", value: "DEBIT" },
	{ label: "Despesa", value: "CREDIT" },
];

const BottomSheetCategories = ({
	myCategory,
}: {
	myCategory: Category | null;
}) => {
	const [categoryFunction, setCategoryFunction] = useState("");
	const [type, setType] = useState<string>(``);
	const { setCategoryData, categoriesData } = useCategoryStore();
	const { profileData } = useProfileStore();
	const categoryService = new CategoryService();

	const handleSubmit = async ({
		name,
		categorytId,
	}: {
		name: string;
		categorytId?: string;
	}) => {
		const profileId = profileData.profileId;
		if (!profileId) return;
		const data = {
			iconId: "bb4ab3ae-ac20-40ba-8a25-de4ff9ca790d",
			name: name,
			type: type,
		};
		switch (categoryFunction) {
			case "edit": {
				if (!categorytId) return console.log("CategoryId not found");

				let dataChanged = { ...data, id: categorytId };
				const response = await categoryService.updateCategory(
					dataChanged
				);
				if (response.profile) {
					alert("Carteira editada com sucesso");
					if (categoriesData) {
						const categorySupport = categoriesData.map((wallet) => {
							if (wallet.id === response.id) return response;
							return wallet;
						});
						setCategoryData(categorySupport);
					}
				}
				break;
			}
			case "delete": {
				if (!categorytId)
					return console.log("CategoryId not found delete");
				const response = await categoryService.deleteCategory(
					categorytId
				);
				if (response) {
					alert("Categoria deletada com sucesso");
					if (categoriesData) {
						const categorySupport = categoriesData.filter(
							(category) => category.id !== categorytId
						);
						setCategoryData(categorySupport);
					}
				}
				break;
			}
			case "create": {
				let dataChanged = { ...data, profileId: profileId };
				const response = await categoryService.createCategory(
					dataChanged
				);
				if (response.profile) {
					alert("Categoria criada com sucesso");
					setCategoryData(response);
				}
				break;
			}
		}
	};

	return (
		<ScrollView>
			<View style={[BottomSheetStyles.container]}>
				<View style={BottomSheetStyles.titleContainer}>
					<MainText style={[BottomSheetStyles.title]}>
						{myCategory ? "editar" : "criar nova"}{" "}
						<MainText bold style={BottomSheetStyles.title}>
							categoria
						</MainText>
					</MainText>
				</View>
				<Formik
					initialValues={{
						name: myCategory ? myCategory.name : "",
						categorytId: "",
					}}
					onSubmit={(values) => handleSubmit(values)}
					enableReinitialize
				>
					{({ handleChange, handleBlur, handleSubmit, values }) => (
						<>
							<TextField
								label="Nome da categoria"
								pholder="Insira um nome para sua categoria"
								style={{ marginBottom: 20 }}
								onChangeText={handleChange("name")}
								onBlur={handleBlur("name")}
								value={values.name}
							/>
							<View style={{ gap: 8, marginBottom: 20 }}>
								<Selection
									label="tipo"
									pholder="selecione o tipo de categoria"
									dataList={TYPE_LIST}
									value={myCategory?.type || type}
									setValue={setType}
								/>
							</View>
							{myCategory ? (
								<View
									style={{
										display: "flex",
										flexDirection: "row",
										justifyContent: "space-between",
									}}
								>
									<MainButton
										onPress={() => {
											(values.categorytId =
												myCategory.id),
												setCategoryFunction("edit"),
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
											(values.categorytId =
												myCategory.id),
												setCategoryFunction("delete"),
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
										setCategoryFunction("create"),
											handleSubmit();
									}}
								>
									<MainText variant="body1" bold>
										adicionar
									</MainText>
								</MainButton>
							)}
						</>
					)}
				</Formik>
			</View>
		</ScrollView>
	);
};

export default BottomSheetCategories;
