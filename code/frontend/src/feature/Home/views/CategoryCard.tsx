import React from 'react';
import { Box, Typography } from '@mui/material';
import ViewInArIcon from '@mui/icons-material/ViewInAr';
import { CategoryCardContent, IconSection } from './styles/CategoryCard.styled';

interface CategoryCardProps {
    name?: string;
}

export default function CategoryCard({ name}: CategoryCardProps) {
    return (
        <CategoryCardContent>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: 2 }}>
                <IconSection>
                    <ViewInArIcon sx={{ fontSize: 30 }}/>
                </IconSection>
                <Typography variant='h5'>
                    {name}
                </Typography>
            </Box>
        </CategoryCardContent>
    )
}
