import styled from "styled-components/native";

import { PillProps } from "./Pill";

export const Section = styled.View<PillProps>`
	background-color: ${({ theme }) => theme.colors.lightGrey};
	padding: 15px 15px;
	${({ variant }) =>
		variant === "small"
			? `padding: 1px 10px`
			: variant === "medium"
			? `padding: 10px 20px`
			: ""};
	border-radius: 20px;
`;
