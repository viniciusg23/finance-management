import styled from "styled-components/native";
import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

export const Container = styled.View`
    display: flex;
    align-items: center;
    justify-content: center;
    height: 85vh;
`;

export const MainContainer = styled.View`
    display:flex;
    align-items: center;
    justify-content: space-between;
    height: 100%;
`;

export const TitleSection = styled.View`
	display: flex;
    flex-direction: row;
	justify-content: center;
	align-items: center;
    gap: 8px;
`;

export const LabelTitleSection = styled.View`
    display: flex;
    align-items: flex-start;
    margin: 8px 0;
`;

export const InputsSection = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
	row-gap: 10px;
`;

export const LogoutSection = styled.View`
    margin: 20px 0;
    align-items: center;
    justify-content: center;
`;

export const additionalStyles = StyleSheet.create({
    inputStyles: {
        width: 300,
    },
    sectionLabel: {
        color: colors.darkGray,
        alignSelf: 'flex-start'
    },
    labelError: {
        fontSize: 12,
		fontWeight: 'bold',
		color: colors.error,
    }
});

