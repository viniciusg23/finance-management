import { Formik, FormikProps } from "formik";
import React from "react";
import { Alert, Pressable, SafeAreaView } from "react-native";
import * as Yup from "yup";
import { StackNavigationProp } from "@react-navigation/stack";
import MainButton from "shared/components/atoms/MainButton";
import MainText from "shared/components/atoms/MainText";
import TextField from "shared/components/molecules/TextField";
import colors from "shared/theme/colors";
import { RegisterTextsEnum } from "../utils/RegisterTextsEnum";
import {
	ButtonsContainer,
	Container,
	InputContainer,
	LinkContainer,
	MainContainer,
	TitleSection,
	additionalStyles,
} from "./styles/Register.styled";
import AuthService from "services/AuthService";
import { useProfileStore } from "store/ProfileStore";

interface RegisterProps {
	navigation: StackNavigationProp<any>;
}

const Register: React.FC<RegisterProps> = ({ navigation }) => {

	const { profileData } = useProfileStore();
	interface Values {
		fullName: string;
		nickname: string;
		email: string;
		password: string;
		confirmPassword: string;
	}

	const initialValues: Values = {
		fullName: "",
		nickname: "",
		email: "",
		password: "",
		confirmPassword: "",
	};

	const SignupSchema = Yup.object().shape({
		fullName: Yup.string()
			.min(2, RegisterTextsEnum.shortNameMsg)
			.required(RegisterTextsEnum.requiredName),
		nickname: Yup.string()
			.min(4, RegisterTextsEnum.shortNickname)
			.max(12, RegisterTextsEnum.longNickname)
			.required(RegisterTextsEnum.requiredNickname)
			.matches(/^\S*$/, RegisterTextsEnum.cantSpaceNickname),
		email: Yup.string()
			.email(RegisterTextsEnum.invalidEmail)
			.required(RegisterTextsEnum.requiredEmail),
		password: Yup.string().required(RegisterTextsEnum.requiredPassword),
		confirmPassword: Yup.string().oneOf(
			[Yup.ref("password")],
			RegisterTextsEnum.dontMatchPasswords
		),
	});

	const handleToLogin = () => {
		navigation.navigate("login");
	};

	const handleRegister = async (values: Values) => {
		const result = await AuthService.onRegister({ email: values.email, name: values.fullName, nickname: values.nickname, password: values.password });
		if(result instanceof Object) {
			Alert.alert(RegisterTextsEnum.title2,RegisterTextsEnum.successMsg);
			if (profileData?.profileId) {
				navigation.navigate("application", { profileId: profileData.profileId });
			  } else {
				navigation.navigate("allProfiles");
			  }
			return;
		}
		alert(result);
		return;
	}

	return (
		<SafeAreaView
			style={{backgroundColor: colors.background}}
		>
			<Container>
				<MainContainer>
					<TitleSection>
						<MainText
							variant="body1"
							style={{ fontSize: 24, lineHeight: 36 }}
						>
							{RegisterTextsEnum.title}
						</MainText>
						<MainText variant="title" bold>
							{RegisterTextsEnum.title2}
						</MainText>
					</TitleSection>
					<InputContainer>
						<Formik
							initialValues={initialValues}
							onSubmit={(values) => handleRegister(values)}
							validationSchema={SignupSchema}
						>
							{({
								values,
								errors,
								touched,
								handleChange,
								setFieldTouched,
								isValid,
								handleSubmit,
							}: FormikProps<Values>) => (
								<InputContainer>
									<TextField
										label=""
										pholder={RegisterTextsEnum.iptName}
										value={values.fullName}
										onChangeText={handleChange("fullName")}
										style={additionalStyles.inputStyles}
										onBlur={() =>
											setFieldTouched("fullName")
										}
									/>
									{touched.fullName && errors.fullName && (
										<MainText
											style={additionalStyles.labelError}
										>
											{errors.fullName}
										</MainText>
									)}
									<TextField
										label=""
										pholder={RegisterTextsEnum.iptNickname}
										value={values.nickname}
										onChangeText={handleChange("nickname")}
										style={additionalStyles.inputStyles}
										onBlur={() =>
											setFieldTouched("nickname")
										}
									/>
									{touched.nickname && errors.nickname && (
										<MainText
											bold
											style={additionalStyles.labelError}
										>
											{errors.nickname}
										</MainText>
									)}
									<TextField
										label=""
										pholder={RegisterTextsEnum.iptEmail}
										value={values.email}
										onChangeText={handleChange("email")}
										style={additionalStyles.inputStyles}
										inputMode="email"
										onBlur={() => setFieldTouched("email")}
									/>
									{touched.email && errors.email && (
										<MainText
											style={additionalStyles.labelError}
										>
											{errors.email}
										</MainText>
									)}
									<TextField
										label=""
										pholder={RegisterTextsEnum.iptPassword}
										value={values.password}
										onChangeText={handleChange("password")}
										style={additionalStyles.inputStyles}
										secureTextEntry
										onBlur={() =>
											setFieldTouched("password")
										}
									/>
									{touched.password && errors.password && (
										<MainText
											style={additionalStyles.labelError}
										>
											{errors.password}
										</MainText>
									)}
									<TextField
										label=""
										pholder={
											RegisterTextsEnum.iptConfirmPassword
										}
										value={values.confirmPassword}
										onChangeText={handleChange(
											"confirmPassword"
										)}
										style={additionalStyles.inputStyles}
										secureTextEntry
										onBlur={() =>
											setFieldTouched("confirmPassword")
										}
									/>
									{touched.confirmPassword &&
										errors.confirmPassword && (
											<MainText
												style={
													additionalStyles.labelError
												}
											>
												{errors.confirmPassword}
											</MainText>
										)}
									<ButtonsContainer
										style={{ marginVertical: 20 }}
									>
										<MainButton
											style={additionalStyles.btnStyle}
											onPress={() => handleRegister(values)}
										>
											<MainText
												variant="body1"
												style={{
													color: colors.background,
												}}
												bold
											>
												{RegisterTextsEnum.btnLabel}
											</MainText>
										</MainButton>
										<LinkContainer>
										<MainText variant="body1">
											{RegisterTextsEnum.linkLabel1}
										</MainText>
										<Pressable onPress={handleToLogin}>
											<MainText
												variant="body1"
												bold
												style={
													additionalStyles.linkStyle
												}
											>
												{RegisterTextsEnum.linkLabel2}
											</MainText>
										</Pressable>
										</LinkContainer>
									</ButtonsContainer>
								</InputContainer>
							)}
						</Formik>
					</InputContainer>
				</MainContainer>
			</Container>
		</SafeAreaView>
	);
};

export default Register;
