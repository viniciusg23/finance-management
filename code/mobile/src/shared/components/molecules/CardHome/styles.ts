import styled from "styled-components/native";

export const Section = styled.View`
	display: flex;
	flex-direction: row;
	align-items: center;
	column-gap: 16px;
`;

export const Icon = styled.View<{ bgIcon: string | undefined }>`
	display: flex;
	align-items: center;
	justify-content: center;
	height: 40px;
	width: 40px;
	border-radius: 10px;
	background-color: ${({ bgIcon }) => (bgIcon ? bgIcon : "#F1F2F3")};
`;

export const Text = styled.View`
	display: flex;
	flex-direction: column;
	justify-content: center;
`;
