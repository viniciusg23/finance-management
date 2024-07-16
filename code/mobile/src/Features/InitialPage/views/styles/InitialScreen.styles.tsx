import styled from "styled-components/native";
import { StyleSheet } from "react-native";
import colors from "shared/theme/colors";

export const Container = styled.View`
    display: flex;
    justify-content: center;
    width: 80%;
`

export const TitleContainer = styled.View`
    display: flex;
    flex-direction: row;
    justify-content: center;
    gap: 8px;
`

export const BtnContainer = styled.View`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: "center";
    gap: 10px;
`

export const aditionalStyles = StyleSheet.create({
    btnStyle: {
        width: 300,
        backgroundColor: colors.mediumGreen,
        borderColor: "transparent",
        alignSelf: "center",
    },
});