import { View } from "react-native";
import React from "react";
import MainText from "shared/components/atoms/MainText";
import Pill from "shared/components/atoms/Pill";
import {
	LeftColumnContainer,
	LeftContainer,
	LeftIconContainer,
	PillContainer,
	RightContainer,
	Section,
} from "./style";

const TransferContainer = () => {
	return (
		<Section>
			<LeftContainer>
				<LeftIconContainer>
					<MainText variant="xtitle" bold>
						💵
					</MainText>
				</LeftIconContainer>

				<LeftColumnContainer>
					<MainText variant="input" bold>
						salário
					</MainText>
					<PillContainer>
						<Pill
							style={{ backgroundColor: "#93eb33" }}
							variant="small"
						>
							<MainText bold>recebido</MainText>
						</Pill>
						<MainText variant="caption">| Santander</MainText>
					</PillContainer>
					<MainText variant="caption">trabalho</MainText>
				</LeftColumnContainer>
			</LeftContainer>

			<RightContainer>
				<MainText variant="caption">único</MainText>
				<MainText variant="input" bold style={{ color: "#7ec92d" }}>
					R$ 299,00
				</MainText>
				<MainText variant="caption">qui, dia 25</MainText>
			</RightContainer>
		</Section>
	);
};

export default TransferContainer;
