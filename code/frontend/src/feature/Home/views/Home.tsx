import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import DehazeIcon from "@mui/icons-material/Dehaze";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import LogoutIcon from "@mui/icons-material/Logout";
import PersonIcon from "@mui/icons-material/Person";
import { Box, Typography } from "@mui/material";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Container, Sidenav } from "shared/components";
import { useAuthStore } from "shared/stores/Auth/AuthStore";
import { Storage } from "shared/utils/Storage";
import { formatDate } from "shared/utils/helperFunctions";
import { useHome } from "../hooks/useHome";
import { useLoadProfiles } from "../hooks/useLoadProfiles";
import ProfileSection from "./ProfileSection";
import {
	AccountInfoSection,
	AccountSection,
	AvatarLabel,
	AvatarSection,
	Card,
	CardSection,
	CardText,
	CategoriesSection,
	Content,
	Header,
	IconSection,
	MainContent,
	ProfilesSection,
	Title,
	UserContent,
	UserDataCard,
	UserDataSection,
	UserDataText,
	UserSection,
} from "./styles/Home.styled";

export default function Home() {
	const {
		user,
		profilesCounts,
		invitesReceived,
		invitesSent,
		handleUserData,
		handleLoadReceivedProfileInvites,
		handleLoadAllProfileSentInvites,
		handleLoadUserProfilesCount,
	} = useHome();

	const { profiles, handleGetProfiles } = useLoadProfiles();

	const navigate = useNavigate();
	const { resetUserData } = useAuthStore();

	useEffect(() => {
		handleUserData();
		handleLoadUserProfilesCount();
		handleLoadReceivedProfileInvites();
		handleLoadAllProfileSentInvites();
		handleGetProfiles();
	}, []);

	const renderDate = () => {
		if (!user?.createdAt) return "--/--/--";
		return formatDate(user?.createdAt);
	};

	const renderProfiles = () => {
		if (profiles?.ownm.length === 0 && profiles.shared.length === 0) {
			return <CardText sx={{ fontSize: 12, fontWeight: 100 }}>Você não possui perfis criados.</CardText>;
		}
		return (
			<>
				{profiles?.ownm.map((profile) => (
					<ProfileSection id={profile.id} name={profile.name} />
				))}
				{profiles?.shared.map((profile) => (
					<ProfileSection id={profile.id} name={profile.name} />
				))}
			</>
		);
	};

	const handleLogout = () => {
		Storage.deleteUserId();
		Storage.deleteUserToken();
		resetUserData();
		navigate("/", { replace: true });
	};

	return (
		<Box sx={{ display: "flex", marginTop: 8}}>
			<Sidenav />
			<Container>
				<Content>
					<MainContent>
						<AccountSection>
							<AccountInfoSection>
								<Header>
									<Title>Resumo</Title>
								</Header>
								<CardSection>
									<Card>
										<CardText>{profilesCounts}</CardText>
										<CardText>Perfis cadastrados</CardText>
									</Card>
									<Card>
										<CardText>{invitesReceived}</CardText>
										<CardText>Convites recebidos</CardText>
									</Card>
									<Card>
										<CardText>{invitesSent}</CardText>
										<CardText>Convites enviados</CardText>
									</Card>
								</CardSection>
							</AccountInfoSection>
							<CategoriesSection>
								<Header>
									<Title>
										Minhas categorias
									</Title>
								</Header>
								<ProfilesSection>
									{renderProfiles()}
								</ProfilesSection>
							</CategoriesSection>
						</AccountSection>
						<UserSection>
							<UserContent>
								<AvatarSection>
									<IconSection>
										<PersonIcon sx={{ fontSize: 100 }} />
									</IconSection>
									<AvatarLabel>
										<Typography sx={{ fontSize: 32 }}>
											{user?.name}
										</Typography>
										<Typography sx={{ fontSize: 18 }}>
											{user?.nickname}
										</Typography>
									</AvatarLabel>
								</AvatarSection>
								<UserDataSection>
									<UserDataCard>
										<Box
											sx={{
												display: "flex",
												alignItems: "center",
												justifyContent: "space-between",
												gap: 2,
											}}
										>
											<IconSection>
												<EmailOutlinedIcon />
											</IconSection>
											<UserDataText>
												{user?.email}
											</UserDataText>
										</Box>
										<DehazeIcon />
									</UserDataCard>
									<UserDataCard>
										<Box
											sx={{
												display: "flex",
												alignItems: "center",
												justifyContent: "space-between",
												gap: 2,
											}}
										>
											<IconSection>
												<CalendarMonthIcon />
											</IconSection>
											<UserDataText>
												{`desde: ${renderDate()}`}
											</UserDataText>
										</Box>
										<DehazeIcon />
									</UserDataCard>
									<UserDataCard
										onClick={() => handleLogout()}
										sx={{
											cursor: "pointer",
											"&:hover": {
												svg: { color: "red" },
												typography: {
													color: "red",
												},
											},
										}}
									>
										<Box
											sx={{
												display: "flex",
												alignItems: "center",
												justifyContent: "space-between",
												gap: 2,
											}}
										>
											<IconSection>
												<LogoutIcon />
											</IconSection>
											<UserDataText sx={{ fontSize: 18 }}>
												sair do finapp
											</UserDataText>
										</Box>
										<DehazeIcon />
									</UserDataCard>
								</UserDataSection>
							</UserContent>
						</UserSection>
					</MainContent>
				</Content>
			</Container>
		</Box>
	);
}
