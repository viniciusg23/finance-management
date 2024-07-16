import styled, { css } from "styled-components/native";

import { MainTextProps } from "./MainText";

export const Section = styled.Text<MainTextProps>`
	${({ theme, variant }) => theme.textVariantes[variant ?? "body3"]};
	color: ${({ color }) => color};

	${(props) =>
		props.bold &&
		css`
			font-family: ${({ theme }) => theme.fontFamily.bold};
		`}
`;
