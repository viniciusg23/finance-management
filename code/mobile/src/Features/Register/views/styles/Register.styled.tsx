import styled from "styled-components/native";
import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

export const MainContainer = styled.View`
    display:flex;
    align-items: center;
    justify-content: space-between;
`

export const TitleSection = styled.View`
	display: flex;
    flex-direction: row;
	justify-content: flex-start;
	align-items: center;
    gap: 8px;
    margin-bottom: 12px;
`;

export const Container = styled.View`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
`;

export const InputContainer = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 12px;
`;

export const LinkContainer = styled.View`
    flex-direction: row;
    align-items: center;
`

export const ButtonsContainer = styled.View `
    align-items: center;
    margin: 20px 0;
    gap: 16px;
`

export const additionalStyles = StyleSheet.create({
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
    },
    labelError: {
        fontSize: 12,
		fontWeight: 'bold',
		color: colors.error,
    }
});