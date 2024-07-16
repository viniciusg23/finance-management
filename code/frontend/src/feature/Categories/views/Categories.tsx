import { Box } from '@mui/material'
import React from 'react'
import { Container, Sidenav } from 'shared/components'

export default function Categories() {
    return (
        <Box sx={{ display: 'flex' }}>
            <Sidenav />
            <Container>
                <h1>Categories</h1>
            </Container>
        </Box>
    )
}
