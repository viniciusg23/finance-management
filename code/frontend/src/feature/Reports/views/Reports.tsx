import { Box } from '@mui/material'

import React from 'react'
import { Container, Sidenav } from 'shared/components'

export default function Reports() {
    return (
        <Box sx={{ display: 'flex' }}>
            <Sidenav />
            <Container>
             <h1>Relatórios</h1>
            </Container>
        </Box>
    )
}
