package com.finapp.financemanagement;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.finapp.financemanagement.domain.dto.user.GetAllUsersResponseDTO;
import com.finapp.financemanagement.domain.dto.user.GetUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.LoginUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.LoginUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.SaveUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.SaveUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.UpdateUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.UpdateUserResponseDTO;
import com.finapp.financemanagement.domain.exception.user.EmailAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.user.InvalidLoginCredentialsException;
import com.finapp.financemanagement.domain.exception.user.NicknameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.user.NicknameNotFoundException;
import com.finapp.financemanagement.domain.exception.user.UserNotFoundException;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.IUserRepository;
import com.finapp.financemanagement.domain.service.AuthorizationService;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.domain.service.SharingService;
import com.finapp.financemanagement.domain.service.UserService;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private ProfileService profileService;

    @Mock
    private SharingService sharingService;

    private User user;
    private UUID userId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário de Teste")
                .nickname("usuarioTeste")
                .email("usuario@teste.com")
                .password("senha123")
                .createdAt(LocalDateTime.now())
                .build();
        userId = user.getId();
    }

    @Test
    public void testFindById_ShouldReturnUserWhenFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findById(userId);

        assertEquals(user, result);
    }

    @Test
    public void testFindById_ShouldThrowUserNotFoundExceptionWhenNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(userId));
    }

    @Test
    public void testFindByNickname_ShouldReturnUserWhenFound() {
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        User result = userService.findByNickname(user.getNickname());

        assertEquals(user, result);
    }

    @Test
    public void testFindByNickname_ShouldThrowNicknameNotFoundExceptionWhenNotFound() {
        when(userRepository.findByNickname(any(String.class))).thenReturn(Optional.empty());

        assertThrows(NicknameNotFoundException.class, () -> userService.findByNickname(user.getNickname()));
    }

    @Test
    public void testGet_ShouldReturnGetUserResponseDTOWhenFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        GetUserResponseDTO result = userService.get(userId);

        assertEquals(new GetUserResponseDTO(user), result);
    }

    @Test
    public void testGetAll_ShouldReturnGetAllUsersResponseDTO() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        GetAllUsersResponseDTO result = userService.getAll();

        assertEquals(new GetAllUsersResponseDTO(users.stream().map(GetUserResponseDTO::new).toList()), result);
    }

    @Test
    public void testSave_ShouldSaveUserAndReturnSaveUserResponseDTO() {
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO("Nome Completo", "novoNickname",
                "novoUsuario@email.com", "senhaNova");

        when(userRepository.findUserByEmail(saveUserRequestDTO.email())).thenReturn(Optional.empty());
        when(userRepository.findByNickname(saveUserRequestDTO.nickname())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        SaveUserResponseDTO result = userService.save(saveUserRequestDTO);

        assertEquals(new SaveUserResponseDTO(user), result);
    }

    @Test
    public void testSave_ShouldThrowEmailAlreadyExistsExceptionWhenEmailAlreadyExists() {
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO("Nome Completo", "novoNickname",
                "usuario@teste.com", "senhaNova"); // Email já existente

        when(userRepository.findUserByEmail(saveUserRequestDTO.email())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class, () -> userService.save(saveUserRequestDTO));
    }

    @Test
    public void testSave_ShouldThrowNicknameAlreadyExistsExceptionWhenNicknameAlreadyExists() {
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO("Nome Completo", "usuarioTeste",
                "novoUsuario@email.com", "senhaNova"); // Nickname já existente

        when(userRepository.findByNickname(saveUserRequestDTO.nickname())).thenReturn(Optional.of(user));

        assertThrows(NicknameAlreadyExistsException.class, () -> userService.save(saveUserRequestDTO));
    }

    @Test
    public void testAuthenticate_ShouldReturnLoginUserResponseDTOWhenCredentialsAreValid() {
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO("usuario@teste.com", "senha123");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUserRequestDTO.email(), loginUserRequestDTO.password());

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
        when(authorizationService.generateToken(user)).thenReturn("tokenDeAcesso");

        LoginUserResponseDTO result = userService.authenticate(loginUserRequestDTO);

        assertEquals(new LoginUserResponseDTO(user.getId(), user.getName(), user.getNickname(), "tokenDeAcesso"), result);
    }

    @Test
    public void testAuthenticate_ShouldThrowInvalidLoginCredentialsExceptionWhenCredentialsAreInvalid() {
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO("usuario@teste.com", "senhaIncorreta");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUserRequestDTO.email(), loginUserRequestDTO.password());

        when(authenticationManager.authenticate(authenticationToken))
                .thenThrow(new InvalidLoginCredentialsException());

        assertThrows(InvalidLoginCredentialsException.class,
                () -> userService.authenticate(loginUserRequestDTO));
    }

    @Test
    public void testUpdate_ShouldUpdateUserAndReturnUpdateUserResponseDTO() {
        UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO("Novo Nome Completo", "novoNickname",
                "novoEmail@email.com");

        when(userRepository.findUserByEmail(updateUserRequestDTO.email())).thenReturn(Optional.empty());
        when(userRepository.findByNickname(updateUserRequestDTO.nickname())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserResponseDTO result = userService.update(user, updateUserRequestDTO);

        assertEquals(new UpdateUserResponseDTO(user), result);
    }

    @Test
    public void testUpdate_ShouldThrowEmailAlreadyExistsExceptionWhenEmailAlreadyExists() {
        UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO("Novo Nome Completo", "novoNickname",
                "usuario@teste.com"); // Email já existente

        when(userRepository.findUserByEmail(updateUserRequestDTO.email())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class, () -> userService.update(user, updateUserRequestDTO));
    }

    @Test
    public void testUpdate_ShouldThrowNicknameAlreadyExistsExceptionWhenNicknameAlreadyExists() {
        UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO("Novo Nome Completo", "usuarioTeste",
                "novoEmail@email.com"); // Nickname já existente

        when(userRepository.findByNickname(updateUserRequestDTO.nickname())).thenReturn(Optional.of(user));

        assertThrows(NicknameAlreadyExistsException.class, () -> userService.update(user, updateUserRequestDTO));
    }

    @Test
    public void testDelete_ShouldDeleteUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.delete(user);
    }

    @Test
    public void testDelete_ShouldThrowUserNotFoundExceptionWhenNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.delete(user));
    }
}