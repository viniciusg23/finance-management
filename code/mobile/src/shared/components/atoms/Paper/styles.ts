import styled from "styled-components/native";

export const Section = styled.View<{
	h?: number;
	w?: number;
	bgColor?: string;
	offShadow?: boolean;
	px?: string;
	py?: string;
	borderRadius?: number;
}>`
	${(props) => (props.w ? `width: ${props.w}rem;` : "")}
	min-height: 4rem;
	min-width: 170px;
	height: ${(props) => props.h}rem;
	background-color: ${(props) => props.bgColor ?? "#fff"};
	box-shadow: ${(props) =>
		props.offShadow ? "0px 0px 5px rgba(0, 0, 0, 0.10)" : "none"};
	border-radius: ${(props) => props.borderRadius ?? "8px"};
	padding-top: ${(props) => props.py ?? "10"}px;
	padding-bottom: ${(props) => props.py ?? "10"}px;
	padding-left: ${(props) => props.px ?? "10"}px;
	padding-right: ${(props) => props.px ?? "10"}px;
	margin: 10px 0;
	flex: auto;
	justify-content: center;
`;
