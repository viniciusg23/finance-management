import { Box, Typography, styled } from "@mui/material";
import { colors } from "shared/theme/theme";

export const Content = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    backgroundColor: '#EEE',
});

export const MainContent = styled(Box)({
    display: 'flex',
    flexDirection: 'row'
})

export const Header = styled(Box)({
    display: 'flex',
    justifyContent: 'flex-start',
    alignItems: 'center',
    paddingLeft: 16,
    paddingRight: 16,
});

export const Title = styled(Typography)({
    fontSize: 18,
    fontWeight: 'bold'
});

export const CardSection = styled(Box)({
    display: 'flex',
    textAlign: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    padding: 24,
    gap: 16,
})

export const Card = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column',
    borderRadius: 8,
    gap: 16,
    padding: 16,
    flex: 1,
    background: '#dbf1e5'
});

export const CardText = styled(Typography)({
    fontSize: 18,
    fontWeight: 600,
});

export const UserSection = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    gap: 16,
    radius: 8,
    padding: 24,
    flex: 1,
});

export const UserContent = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    backgroundColor: '#FFF',
    borderRadius: 8,
    padding: 24,
    gap: 24,
})

export const AvatarSection = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column',
    gap: 16,
});

export const IconSection = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#dbf1e5',
    padding: 8,
    borderRadius: '100%',
});

export const AvatarLabel = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
    flexDirection: 'column',
    gap: 8,
});

export const AccountSection = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    gap: 16,
    radius: 8,
    padding: 24,
});

export const AccountInfoSection = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    backgroundColor: '#FFF',
    borderRadius: 8,
    padding: 24,
});

export const UserDataSection = styled(Box)({
    display: 'flex',
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
    flexDirection: 'column',
    gap: 8,
    borderRadius: 8,
    backgroundColor: '#FFF',
});

export const UserDataCard = styled(Box)({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    flexDirection: 'row',
    width: '100%',
    backgroundColor: '#dbf1e5',
    gap: 16,
    padding: 16,
    borderRadius: 8,
});

export const UserDataText = styled(Typography)({
    fontSize: 16,
})

export const CategoriesSection = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    backgroundColor: '#FFF',
    borderRadius: 8,
    padding: 24,
    flex: 1,
});

export const ProfilesSection = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    gap: 12,
    borderRadius: 8,
    padding: 24,
    flex: 1,
    overflowY: 'auto',
    maxHeight: '500px',
})