import { Box, Paper, PaperProps, Typography, styled } from "@mui/material";
import { colors } from "shared/theme/theme";

interface CardContainerProps extends PaperProps {
    background?: string;
}

export const CardContainer = styled(Paper)({
    margin: 32,
    padding: 16,
    minWidth: 320,
    maxWidth: 320,
    borderRadius: 16
});

export const CardBackGround = styled(Paper)<CardContainerProps>(({ background }) => ({
    padding: 16,
    backgroundColor: background ?? colors.lightGreen,
    borderRadius: 16
}));

export const CardContent = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    padding: 16,
    gap: 16,
    borderRadius: 16,
    backgroundColor: '#FFF'
})

export const TitleSection = styled(Box)({
    display: 'flex',
    alignItems: 'center',
    gap: 16,
    padding: 4,
})

export const CardText = styled(Typography)({
    color: colors.black,
    fontWeight: '600'
});

export const IconSection = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
    backgroundColor: '#FFF',
    borderRadius: '100%',
});