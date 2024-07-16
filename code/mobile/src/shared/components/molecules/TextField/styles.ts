import styled from "styled-components/native";

export const InputText = styled.TextInput`
	border: 1px solid ${({ theme }) => theme.colors.primary};
	border-radius: 30px;
	padding: 16px;
	width: 100%;
	font-family: ${({ theme }) => theme.fontFamily.medium};
	font-size: 14px;
`;

export const Section = styled.View`
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	width: 100%;
	
`;