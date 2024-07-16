import { Box, Paper, styled } from "@mui/material";

export const CategoryCardContent = styled(Paper)({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    flexDirection: 'row',
    backgroundColor: '#FFF',
    gap: 16,
    flex: 1,
    padding: 16,
    borderRadius: 16,
});

export const IconSection = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
    backgroundColor: '#FFF',
    borderRadius: '100%',
});