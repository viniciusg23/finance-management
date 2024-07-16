import { Box, Paper } from '@mui/material'
import styled from 'styled-components'

const StyledPaper = styled(Box)({
    display: "flex",
    flexDirection: 'column',
    flex: 1,
    backgroundColor: '#EEE',
});

interface ContainerProps {
    children: React.ReactNode;
}

export default function Container({ children }: ContainerProps) {
    return (
        <StyledPaper>
            <Box sx={{ flex: 1, height: '100%' }}>

                {children}
            </Box>
        </StyledPaper>
        );
}