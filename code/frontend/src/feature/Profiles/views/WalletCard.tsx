import { Box } from '@mui/material';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import { CardBackGround, CardContainer, CardContent, CardText, IconSection, TitleSection } from './styles/WalletCard.styled';

interface WalletCardProps {
    name: string;
    description: string;
    balance: number;
    color: string;
}

export default function WalletCard({ name, description, balance, color }: WalletCardProps) {

    function formatCurrency(value: number) {
        return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value);
    }

    return (
        <CardContainer>
            <CardBackGround background={color}>
                <CardContent>
                    <TitleSection>
                        <IconSection sx={{ border: 1, borderColor: `${color}` }}>
                            <AccountBalanceWalletIcon sx={{ color: `${color}` }} />
                        </IconSection>
                        <CardText variant='h6'>
                            {name}
                        </CardText>
                    </TitleSection>
                    <Box>
                        <CardText>
                            {description}
                        </CardText>
                    </Box>
                    <Box>
                        <CardText variant='h5'>
                            {formatCurrency(balance)}
                        </CardText>
                    </Box>
                </CardContent>
            </CardBackGround>
        </CardContainer>
    )
}
