import { View, TouchableOpacity } from "react-native";
import React, { useCallback, useEffect, useMemo, useRef } from "react";
import { Header } from "./styles/Categories.styled";
import MainText from "shared/components/atoms/MainText";
import BottomSheet, { BottomSheetBackdrop } from "@gorhom/bottom-sheet";
import BottomSheetCategories from "./BottomSheetCategories/BottomSheetCategories";
import { useProfileStore } from "store/ProfileStore";
import { ProfileService } from "services/ProfileService";
import { useCategoryStore, Category } from "store/CategoryStore";
import { CategoryCard } from "./CategoryCard";

const Categories = () => {
	const [myCategory, setMyCategory] = React.useState<Category | null>(null);
	const profileState = useProfileStore();
	const { profileData } = useProfileStore();
	const profileService = new ProfileService();
	const { setCategoryData, categoriesData } = useCategoryStore();
	const bottomSheetRef = useRef<BottomSheet>(null);
	const snapMenu = useMemo(() => ["50%"], []);
	const handleCloseAction = () => bottomSheetRef.current?.close();
	const handleOpenAction = () => {
		setMyCategory(null);
		bottomSheetRef.current?.expand();
	};
	const handleOpenCategoryBottom = (ref: any, category: Category) => {
		ref.current?.expand();
		setMyCategory(category);
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

	useEffect(() => {
		const fetchData = async () => {
			if (profileData.profileId) {
				const result = await profileService.getAllProfileCategories(
					profileData.profileId
				);
				if (result) setCategoryData(result.categories);
			}
		};
		fetchData();
	}, []);

	const renderEmptyCategory = (type: string) => {
		return (
			<View
				style={{
					alignItems: "center",
					justifyContent: "center",
					padding: 32,
				}}
			>
				<MainText
					variant="body2"
					style={{
						marginBottom: 8,
						textAlign: "center",
						flexDirection: "row",
					}}
				>
					você não possui nenhuma categoria de{" "}
					{type === "DEBIT" ? "débito." : " crédito."}
				</MainText>
			</View>
		);
	};

	return (
		<>
			<View style={{ marginHorizontal: 15, marginTop: 50 }}>
				<Header>
					<View
						style={{
							width: "100%",
							flexDirection: "row",
							justifyContent: "space-between",
						}}
					>
						<MainText variant="body1" bold>
							Categorias
						</MainText>
						<TouchableOpacity onPress={handleOpenAction}>
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
						Despesa
					</MainText>
					{categoriesData &&
						categoriesData
							.filter((category) => category.type === "CREDIT")
							.map((category) => (
								<TouchableOpacity
									onPress={() =>
										handleOpenCategoryBottom(
											bottomSheetRef,
											category
										)
									}
								>
									<CategoryCard
										key={category.id}
										name={category.name}
									/>
								</TouchableOpacity>
							))}
					{categoriesData?.length == 0 &&
						renderEmptyCategory("CREDIT")}
				</View>
				<View style={{ marginTop: 8 }}>
					<MainText variant="body1" bold>
						Receita
					</MainText>
					{categoriesData &&
						categoriesData
							.filter((category) => category.type === "DEBIT")
							.map((category) => (
								<TouchableOpacity
									onPress={() =>
										handleOpenCategoryBottom(
											bottomSheetRef,
											category
										)
									}
								>
									<CategoryCard
										key={category.id}
										name={category.name}
									/>
								</TouchableOpacity>
							))}
					{categoriesData?.filter(
						(category) => category.type === "DEBIT"
					).length == 0 && renderEmptyCategory("DEBIT")}
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
				<BottomSheetCategories myCategory={myCategory} />
			</BottomSheet>
		</>
	);
};

export default Categories;
