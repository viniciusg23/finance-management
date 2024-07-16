import { View, ScrollView } from "react-native";
import { Icon } from "./styles";
import React from "react";
import Pill from "shared/components/atoms/Pill";
import MainText from "shared/components/atoms/MainText";
import { Entypo, Octicons, SimpleLineIcons, Feather } from "@expo/vector-icons";
import { TouchableOpacity } from "react-native-gesture-handler";

interface HorizontalPillFilterProps {
	setSelectedFltr: React.Dispatch<React.SetStateAction<string>>;
	selectedFltr: string;
}

const HorizontalPillFIlter = ({
	setSelectedFltr,
	selectedFltr,
}: HorizontalPillFilterProps) => {
	const handleSelectFilter = (selected: string) => {
		setSelectedFltr(selected);
	};

	return (
		<ScrollView
			horizontal
			showsHorizontalScrollIndicator={false}
			style={{ marginBottom: 15 }}
		>
			<View
				style={{
					display: "flex",
					flexDirection: "row",
					columnGap: 10,
				}}
			>
				<TouchableOpacity onPress={() => handleSelectFilter("Geral")}>
					<Pill
						variant="medium"
						style={{
							flexDirection: "row",
							columnGap: 8,
							...{
								backgroundColor:
									selectedFltr === "Geral"
										? "#93eb33"
										: "white",
							},
						}}
					>
						<Icon>
							<Entypo name="list" size={14} color="black" />
						</Icon>
						<MainText variant="input" bold>
							Geral
						</MainText>
					</Pill>
				</TouchableOpacity>
				<TouchableOpacity onPress={() => handleSelectFilter("Cartão")}>
					<Pill
						variant="medium"
						style={{
							flexDirection: "row",
							columnGap: 8,
							...{
								backgroundColor:
									selectedFltr === "Cartão"
										? "#93eb33"
										: "white",
							},
						}}
					>
						<Icon>
							<Octicons
								name="credit-card"
								size={14}
								color="black"
							/>
						</Icon>
						<MainText variant="input" bold>
							Cartão
						</MainText>
					</Pill>
				</TouchableOpacity>
				<TouchableOpacity onPress={() => handleSelectFilter("Conta")}>
					<Pill
						variant="medium"
						style={{
							flexDirection: "row",
							columnGap: 8,
							...{
								backgroundColor:
									selectedFltr === "Conta"
										? "#93eb33"
										: "white",
							},
						}}
					>
						<Icon>
							<SimpleLineIcons
								name="wallet"
								size={14}
								color="black"
							/>
						</Icon>
						<MainText variant="input" bold>
							Conta
						</MainText>
					</Pill>
				</TouchableOpacity>
				<TouchableOpacity
					onPress={() => handleSelectFilter("Categoria")}
				>
					<Pill
						variant="medium"
						style={{
							flexDirection: "row",
							columnGap: 4,
							...{
								backgroundColor:
									selectedFltr === "Categoria"
										? "#93eb33"
										: "white",
							},
						}}
					>
						<Icon>
							<Feather name="grid" size={14} color="black" />
						</Icon>
						<MainText variant="input" bold>
							Categoria
						</MainText>
					</Pill>
				</TouchableOpacity>
			</View>
		</ScrollView>
	);
};

export default HorizontalPillFIlter;
