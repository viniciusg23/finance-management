import { Accordion, AccordionSummary, Typography, AccordionDetails, Box, } from '@mui/material'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useEffect, useState } from 'react'
import WalletCard from './WalletCard'
import { WalletsSection } from './styles/Profiles.styled'
import { ProfileService } from 'services/ProfileService';
import { IGetAllProfileWalletsResponseDTO } from 'shared/dto/IGetAllProfileWalletsResponseDTO';

interface ProfileSectionProps {
    id: string;
    name: string;
}

export default function ProfileSection({ name, id }: ProfileSectionProps) {

    const [wallets, setWallets] = useState<IGetAllProfileWalletsResponseDTO[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            if (id) {
                const result = await ProfileService.getAllProfileWallets(id);
                if (result) setWallets(result.wallets);
            }
        };
        fetchData();
    }, []);


    const renderContent = () => {
        if (wallets?.length === 0) return (
            <Box>
                <Typography>Este perfil não possui carteiras.</Typography>
            </Box>
        )
        return (
            <>
                {wallets?.map((wallet) => (
                    <WalletCard key={wallet.id} name={wallet.name} balance={wallet.balance} description={wallet.description} color={wallet.color} />
                ))}
            </>
        );
    }

    return (
        <Accordion>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1-content"
                id="panel1-header"
            >
                <Typography variant='h5'>{name}</Typography>
            </AccordionSummary>
            <AccordionDetails sx={{
                backgroundColor: '#EEE',
            }}>
                <WalletsSection>
                    {renderContent()}
                </WalletsSection>
            </AccordionDetails>
        </Accordion>
    )
}
