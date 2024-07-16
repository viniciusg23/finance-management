import styled from "styled-components/native";

export const Section = styled.View`
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	padding: 10px 0;
  margin: 5px;
`;

export const RightContainer = styled.View`
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	row-gap: 5px;
`;

export const LeftContainer = styled.View`
	display: flex;
	flex-direction: row;
`;

export const LeftIconContainer = styled.View`
  padding: 15px 10px;
`;

export const LeftColumnContainer = styled.View`
	display: flex;
	flex-direction: column;
	row-gap: 5px;
  padding-left: 10px;
`;

export const PillContainer = styled.View`
	display: flex;
	flex-direction: row;
	align-items: center;
	column-gap: 3px;
`;
