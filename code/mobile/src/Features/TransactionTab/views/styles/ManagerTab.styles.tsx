import styled from "styled-components/native";
import colors from "shared/theme/colors";

export const Container = styled.ScrollView`
	height: 100%;
`;

export const ContainerForm = styled.View`
	margin-bottom: 16px;
`;

export const TitleSection = styled.View`
	flex-direction: row;
	margin: 16px 8px;
`;

export const CardsTab = styled.View`
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: center;
	gap: 16px;
	margin-top: 36px;
	margin-bottom: 36px;
`;

type CardProps = {
	active: boolean;
};

export const Card = styled.TouchableOpacity<CardProps>`
	flex-direction: column;
	width: 150px;
	height: 110px;
	padding: 8px;
	justify-content: center;
	align-items: center;
	border: 1px solid ${colors.secondary};
	background-color: #fff;
	border-radius: 8px;
	gap: 12px;
	${({ active }) =>
		active &&
		`
		border: 3px solid #d1d1d1;
  `}
`;

export const TextSection = styled.View`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`;

export const CardsSection = styled.View`
	margin-left: 15px;
	margin-right: 15px;
`;
