import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import AccountBoxOutlinedIcon from "@mui/icons-material/AccountBoxOutlined";
import AssessmentOutlinedIcon from "@mui/icons-material/AssessmentOutlined";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import { CSSObject, Theme, Toolbar, Typography } from "@mui/material";
import MuiAppBar, { AppBarProps as MuiAppBarProps } from "@mui/material/AppBar";
import MenuIcon from "@mui/icons-material/Menu";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import MuiDrawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import { styled, useTheme } from "@mui/material/styles";
import * as React from "react";
import { useNavigate } from "react-router-dom";

const drawerWidth = 170;

const DrawerHeader = styled("div")(({ theme }) => ({
	display: "flex",
	alignItems: "center",
	justifyContent: "flex-end",
	gap: 16,
	padding: theme.spacing(0, 1),
	// necessary for content to be below app bar
	...theme.mixins.toolbar,
}));

const openedMixin = (theme: Theme): CSSObject => ({
	width: drawerWidth,
	transition: theme.transitions.create("width", {
		easing: theme.transitions.easing.sharp,
		duration: theme.transitions.duration.enteringScreen,
	}),
	overflowX: "hidden",
  backgroundColor: '#393939',
});

const closedMixin = (theme: Theme): CSSObject => ({
	transition: theme.transitions.create("width", {
		easing: theme.transitions.easing.sharp,
		duration: theme.transitions.duration.leavingScreen,
	}),
	overflowX: "hidden",
	width: `calc(${theme.spacing(7)} + 1px)`,
	[theme.breakpoints.up("sm")]: {
		width: `calc(${theme.spacing(8)} + 1px)`,
	},
});

interface AppBarProps extends MuiAppBarProps {
	open?: boolean;
}

const AppBar = styled(MuiAppBar, {
	shouldForwardProp: (prop) => prop !== "open",
})<AppBarProps>(({ theme, open }) => ({
	zIndex: theme.zIndex.drawer + 1,
	backgroundColor: "#4ab77f",
	boxShadow: "none",
}));

const Drawer = styled(MuiDrawer, {
	shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
	width: drawerWidth,
	flexShrink: 0,
	whiteSpace: "nowrap",
	boxSizing: "border-box",
	...(open && {
		...openedMixin(theme),
		"& .MuiDrawer-paper": openedMixin(theme),
	}),
}));

export default function Sidenav() {
	const theme = useTheme();
	const [open, setOpen] = React.useState(false);
	const navigate = useNavigate();

	return (
		<Box sx={{ display: "flex", backgroundColor: "#393939" }}>
			<AppBar position="fixed" open={false}>
				<Toolbar>
					<Typography variant="h6" noWrap component="div">
						FinApp
					</Typography>
				</Toolbar>
			</AppBar>
			<Drawer variant="permanent" open={true}>
				<DrawerHeader>
					<Box
						sx={{
							display: "flex",
							alignItems: "center",
							justifyContent: "center",
							gap: 2,
						}}
					>
						<AccountBalanceWalletIcon />
						<Typography variant="h4" id="user-name">
							FinApp
						</Typography>
					</Box>
				</DrawerHeader>
				<Divider />
				<List>
					<ListItem
						disablePadding
						sx={{ display: "block" }}
						onClick={() => navigate("/home")}
					>
						<ListItemButton
							sx={{
								minHeight: 48,
								justifyContent: open ? "initial" : "center",
								px: 2.5,
							}}
						>
							<ListItemIcon
								sx={{
									minWidth: 0,
									mr: open ? 3 : "auto",
									justifyContent: "center",
								}}
							>
								<HomeOutlinedIcon sx={{ color: "#FFF" }} />
							</ListItemIcon>
							<ListItemText
								primary="Home"
								sx={{ color: "#FFF", marginLeft: 2 }}
							/>
						</ListItemButton>
					</ListItem>
					<ListItem
						disablePadding
						sx={{ display: "block" }}
						onClick={() => navigate("/profiles")}
					>
						<ListItemButton
							sx={{
								minHeight: 48,
								justifyContent: open ? "initial" : "center",
								px: 2.5,
								backgroundColor: "#393939",
							}}
						>
							<ListItemIcon
								sx={{
									minWidth: 0,
									mr: open ? 3 : "auto",
									justifyContent: "center",
									backgroundColor: "#393939",
								}}
							>
								<AccountBoxOutlinedIcon
									sx={{ color: "#FFF" }}
								/>
							</ListItemIcon>
							<ListItemText
								primary="Perfis"
								sx={{ color: "#FFF", marginLeft: 2 }}
							/>
						</ListItemButton>
					</ListItem>
					<ListItem
						disablePadding
						sx={{ display: "block" }}
						onClick={() => navigate("/reports")}
					>
						<ListItemButton
							sx={{
								minHeight: 48,
								justifyContent: open ? "initial" : "center",
								px: 2.5,
								backgroundColor: "#393939",
							}}
						>
							<ListItemIcon
								sx={{
									minWidth: 0,
									mr: open ? 3 : "auto",
									justifyContent: "center",
								}}
							>
								<AssessmentOutlinedIcon
									sx={{ color: "#FFF" }}
								/>
							</ListItemIcon>
							<ListItemText
								primary="Relatórios"
								sx={{ color: "#FFF", marginLeft: 2 }}
							/>
						</ListItemButton>
					</ListItem>
				</List>
			</Drawer>
		</Box>
	);
}
