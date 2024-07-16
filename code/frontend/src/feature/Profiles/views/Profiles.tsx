import { useEffect, useState } from 'react';
import { Box, Divider, Paper, Typography } from '@mui/material';
import { Container, Sidenav } from 'shared/components';
import { Header, ProfilesContent, ProfilesSection } from './styles/Profiles.styled';
import { IGetAllProfilesDTO, Profile } from 'shared/dto/IGetAllProfilesDTO';
import { useProfiles } from '../hooks/useProfiles';
import ProfileSection from './ProfileSection';

export default function Profiles() {

  const [profiles, setProfiles] = useState<IGetAllProfilesDTO>();
  const { handleGetProfiles } = useProfiles();

  useEffect(() => {
    const fetchData = async () => {
      await handleGetProfiles(setProfiles);
    }
    fetchData();
  }, []);

  const renderProfiles = (profiles: Profile[] | undefined) => {
    if(profiles){
      if (profiles.length === 0) return <Typography variant='h5'>você não possui perfis desta categoria.</Typography>
      return (
        <ProfilesContent>
          <ProfilesSection>
            {profiles.map((profile) => (
              <ProfileSection key={profile.id} id={profile.id} name={profile.name} />
            ))}
          </ProfilesSection>
        </ProfilesContent>
      )
    }
    return null;
  }

  return (
    <Box sx={{ display: 'flex' }}>
      <Sidenav />
      <Container>
        <Header>
          <Typography variant='h2'>perfis</Typography>
          <Divider />
        </Header>
        <Box sx={{ flexDirection: 'row' }}>
          <Header>
            <Typography variant='h4'>meus perfis</Typography>
            <Divider />
          </Header>
          {renderProfiles(profiles?.ownm)}
        </Box>
        <Box>
          <Header>
            <Typography variant='h4'>outros perfis</Typography>
            <Divider />
          </Header>
          <ProfilesSection>
            {profiles?.shared.map((profile) => (
              <ProfileSection key={profile.id} id={profile.id} name={profile.name} />
            ))}
          </ProfilesSection>
        </Box>
      </Container>
    </Box>
  )
}
