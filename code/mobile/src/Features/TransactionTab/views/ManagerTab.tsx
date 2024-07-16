import React, { useState } from "react";
import { AntDesign } from "@expo/vector-icons";
import {
	Card,
	CardsSection,
	CardsTab,
	Container,
	ContainerForm,
	TextSection,
	TitleSection,
} from "./styles/ManagerTab.styles";
import MainText from "shared/components/atoms/MainText";
import { ManagerTabEnum } from "./utils/ManagerTabEnum";
import colors from "shared/theme/colors";
import { SafeAreaView } from "react-native-safe-area-context";
import { View } from "react-native";
import Selection from "shared/components/molecules/Selection/Selection";
import TextField from "shared/components/molecules/TextField";
import MainButton from "shared/components/atoms/MainButton";

const data = [
	{ label: "Item 1", value: "1" },
	{ label: "Item 2", value: "2" },
	{ label: "Item 3", value: "3" },
	{ label: "Item 4", value: "4" },
	{ label: "Item 5", value: "5" },
	{ label: "Item 6", value: "6" },
	{ label: "Item 7", value: "7" },
	{ label: "Item 8", value: "8" },
];

const ManagerTab = () => {
	const [selected, setSelected] = useState("");

	return (
		<SafeAreaView>
			<Container>
				<TitleSection>
					<MainText variant="body1">{ManagerTabEnum.title}</MainText>
					<MainText variant="body1" bold>
						{ManagerTabEnum.titleBold}
					</MainText>
				</TitleSection>
				<CardsSection>
					<CardsTab>
						<Card
							onPress={() => setSelected("despesa")}
							active={selected === "despesa"}
						>
							<AntDesign
								name="upcircleo"
								size={24}
								color={colors.error}
							/>
							<TextSection>
								<MainText variant="caption">
									{ManagerTabEnum.cardTitle}
								</MainText>
								<MainText
									variant="body2"
									bold
									style={{ fontSize: 16 }}
								>
									{ManagerTabEnum.expense}
								</MainText>
							</TextSection>
						</Card>
						<Card
							onPress={() => setSelected("receita")}
							active={selected === "receita"}
						>
							<AntDesign
								name="downcircleo"
								size={24}
								color={colors.mediumGreen}
							/>
							<TextSection>
								<MainText variant="caption">
									{ManagerTabEnum.cardTitle}
								</MainText>
								<MainText
									variant="body2"
									bold
									style={{ fontSize: 16 }}
								>
									{ManagerTabEnum.revenue}
								</MainText>
							</TextSection>
						</Card>
					</CardsTab>
					<ContainerForm>
						<View style={{ flexDirection: "column", rowGap: 10 }}>
							<Selection
								label="carteira"
								pholder="selecione sua carteira"
								dataList={data}
							/>
							<Selection
								label="categoria"
								pholder="selecione a categoria"
								dataList={data}
							/>
							<TextField
								style={{ backgroundColor: "white" }}
								label="valor"
								pholder="ex.: 20,00"
							/>
							<TextField
								style={{ backgroundColor: "white" }}
								label="titulo"
								pholder="ex.: almoço"
							/>
							<TextField
								style={{
									backgroundColor: "white",
									marginBottom: 20,
								}}
								label="comentário"
								pholder="ex.: almoço com amigos"
							/>
							<MainButton
								style={{ backgroundColor: "white" }}
								onPress={() =>
									alert("Carteira criada com sucesso.")
								}
							>
								<MainText variant="body1" bold>
									adicionar
								</MainText>
							</MainButton>
						</View>
					</ContainerForm>
				</CardsSection>
			</Container>
		</SafeAreaView>
	);
};

export default ManagerTab;
