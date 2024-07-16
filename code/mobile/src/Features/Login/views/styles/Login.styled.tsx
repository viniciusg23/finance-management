import styled from "styled-components/native";
import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

export const TitleSection = styled.View`
	display: flex;
    flex-direction: row;
	justify-content: center;
	align-items: center;
    gap: 8px;
    margin-bottom: 8px;
`;

export const Container = styled.View`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
`;

export const MainContainer = styled.View`
    display:flex;
    align-items: center;
    justify-content: space-between;
`

export const InputContainer = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
	row-gap: 10px;
`;

export const LinkContainer = styled.View`
    display:flex;
    flex-direction: row;
    justify-content: center;
`

export const aditionalStyles = StyleSheet.create({
    inputStyles: {
        width: 300,
        alignSelf: "center"
    },
    btnStyle: {
        width: 300,
        backgroundColor: colors.mediumGreen,
        borderColor: "transparent",
        alignSelf: "center",
    },
    linkStyle: {
        color: colors.mediumGreen
    }
});
