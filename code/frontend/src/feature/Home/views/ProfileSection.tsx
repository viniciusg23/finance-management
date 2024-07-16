import { Accordion, AccordionSummary, Typography, AccordionDetails } from '@mui/material'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useEffect, useState } from 'react'
import { ProfileService } from 'services/ProfileService';
import CategoryCard from './CategoryCard';
import { IGetAllProfilesCategoriesDTO } from 'shared/dto/IGetAllProfilesCategoriesDTO';

interface ProfileSectionProps {
    id: string;
    name: string;
}

export default function ProfileSection({ name, id }: ProfileSectionProps) {

    const [categories, setCategories] = useState<IGetAllProfilesCategoriesDTO>();

    useEffect(() => {
        const fetchData = async () => {
            const response = await ProfileService.getAllProfileCategories(id);
            if (response) {
                setCategories(response);
            }
        }
    
        fetchData();
    }, []);

    const renderContent = () => {
        if(categories?.categories.length === 0) return <Typography variant='h5'>Esse perfil não possui categorias.</Typography>
        return (
            <>
                {categories?.categories.map((categorie) => (
                    <CategoryCard key={categorie.id} name={categorie.name}/>
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
            <AccordionDetails sx={{ display: 'flex', flexDirection: 'column', backgroundColor: '#EEE', borderWidth: 0, gap: 2, overflowY: 'auto' }}>
                {renderContent()}
            </AccordionDetails>
        </Accordion>
    )
}
