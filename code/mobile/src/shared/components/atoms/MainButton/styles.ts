import styled from "styled-components/native";

export const Section = styled.TouchableOpacity`
	align-items: center;
	justify-content: center;
	border: 1px solid ${({ theme }) => theme.colors.primary};
	border-radius: 30px;
	padding: 16px;
	width: 100%;
`;
