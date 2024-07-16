import { Box, Paper, styled } from "@mui/material";

export const Container = styled(Paper)({
    display: "flex",
    flexDirection: 'column',
    padding: 16,
    flex: 1,
    backgroundColor: '#EEE',
    minHeight: '100vh'
})

export const Header = styled(Box)({
    display: 'flex',
    textAlign: 'center',
    justifyContent: 'center',
    marginTop: 16,
    marginBottom: 16,
})

export const ProfilesSection = styled(Box)({
    display: 'flex',
    textAlign: 'center',
    justifyContent: 'center',
    flexDirection: 'column',
    padding: 16,
    gap: 16,
    backgroundColor: '#EEE',
    borderRadius: 16,
    margin: 16,
});

export const ProfilesContent = styled(Box)({
    overflowY: 'auto',
    maxHeight: '640px',
})

export const WalletsSection = styled(Box)({
    display: 'flex',
    flexDirection: 'row',
    gap: 32,
    flexWrap: 'wrap',
})