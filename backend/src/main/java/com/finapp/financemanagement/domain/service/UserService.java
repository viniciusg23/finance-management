package com.finapp.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.dto.profile.GetProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.user.GetAllUserProfilesResponseDTO;
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
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.IUserRepository;

import jakarta.transaction.Transactional;

/**
 * Serviço para gerenciamento de usuários.
*/
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authentaticationManager;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SharingService sharingService;

    /**
     * Busca um usuário pelo seu identificador.
     *
     * @param id Identificador UUID do usuário.
     * @return O usuário encontrado.
     * @throws UserNotFoundException se o usuário não for encontrado.
    */
    public User findById(UUID id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optionalUser.get();
    }
    /**
     * Busca um usuário pelo seu apelido.
     *
     * @param receiverNickname Apelido do usuário.
     * @return O usuário encontrado.
     * @throws UserNotFoundException se o usuário não for encontrado.
    */
    public User findByNickname(String receiverNickname) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByNickname(receiverNickname);

        if (optionalUser.isEmpty()) {
            throw new NicknameNotFoundException();
        }

        return optionalUser.get();
    }
    /**
     * Busca um usuário pelo seu identificador e retorna um DTO.
     *
     * @param id Identificador UUID do usuário.
     * @return O DTO do usuário encontrado.
    */
    public GetUserResponseDTO get(UUID id) {
        User user = this.findById(id);
        return new GetUserResponseDTO(user);
    }

    /**
     * Busca todos os usuários cadastrados no sistema.
     *
     * @return DTO de resposta contendo os detalhes dos usuários.
    */
    public GetAllUsersResponseDTO getAll() {
        List<User> users = this.userRepository.findAll();

        List<GetUserResponseDTO> usersResponse = users.stream().map(user -> new GetUserResponseDTO(user)).toList();

        return new GetAllUsersResponseDTO(usersResponse);
    }

    /**
     * Busca todos os perfis de um usuário autenticado.
     *
     * @param authenticatedUser Usuário autenticado.
     * @return DTO de resposta contendo os detalhes dos perfis.
    */
    public GetAllUserProfilesResponseDTO getAllProfiles(User authenticatedUser) {
        List<Profile> ownmProfiles = this.profileService.findAllByUserId(authenticatedUser.getId());
        List<Profile> sharedProfiles = this.sharingService.findAllProfilesByGuestId(authenticatedUser.getId());

        List<GetProfileResponseDTO> ownmProfilesResponse = ownmProfiles.stream()
                .map(profile -> new GetProfileResponseDTO(profile))
                .toList();

        List<GetProfileResponseDTO> sharedProfilesResponse = sharedProfiles.stream()
                .map(profile -> new GetProfileResponseDTO(profile))
                .toList();

        return new GetAllUserProfilesResponseDTO(ownmProfilesResponse, sharedProfilesResponse);
    }

    /**
     * Salva um novo usuário no sistema.
     *
     * @param data DTO com informações para salvar o usuário.
     * @return DTO com os detalhes do usuário salvo.
     * @throws EmailAlreadyExistsException se o e-mail do usuário já existir.
     * @throws NicknameAlreadyExistsException se o apelido do usuário já existir.
    */
    @Transactional
    public SaveUserResponseDTO save(SaveUserRequestDTO data)
            throws EmailAlreadyExistsException, NicknameAlreadyExistsException {
 
        if (this.userRepository.findUserByEmail(data.email()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        if (this.userRepository.findByNickname(data.nickname()).isPresent()) {
            throw new NicknameAlreadyExistsException();
        }

        User user = User.builder()
                .name(data.name())
                .nickname(data.nickname())
                .email(data.email())
                .build();

        String hashadPassword = new BCryptPasswordEncoder().encode(data.password());
        user.setPassword(hashadPassword);

        User savedUser = this.userRepository.save(user);
        return new SaveUserResponseDTO(savedUser);
    }

    /**
     * Autentica um usuário no sistema.
     *
     * @param data DTO com informações para autenticar o usuário.
     * @return DTO com os detalhes do usuário autenticado.
     * @throws InvalidLoginCredentialsException se as credenciais de login forem inválidas.
    */
    public LoginUserResponseDTO authenticate(LoginUserRequestDTO data) {
        try {
            var userLogin = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication authentication = this.authentaticationManager.authenticate(userLogin);
            User user = (User) authentication.getPrincipal();

            return new LoginUserResponseDTO(this.authorizationService.generateToken(user), user);

        } catch (AuthenticationException e) {
            throw new InvalidLoginCredentialsException();
        }
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para atualizar o usuário.
     * @return DTO de resposta com os detalhes do usuário atualizado.
     * @throws EmailAlreadyExistsException se o e-mail do usuário já existir.
     * @throws NicknameAlreadyExistsException se o apelido do usuário já existir.
    */
    @Transactional
    public UpdateUserResponseDTO update(User authenticatedUser, UpdateUserRequestDTO data)
            throws EmailAlreadyExistsException, NicknameAlreadyExistsException {

        if (this.userRepository.findUserByEmail(data.email()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        if (this.userRepository.findByNickname(data.nickname()).isPresent()) {
            throw new NicknameAlreadyExistsException();
        }

        User user = User.builder()
                .id(authenticatedUser.getId())
                .name(data.name())
                .nickname(data.nickname())
                .email(data.email())
                .build();

        User updatedUser = this.userRepository.save(user);
        return new UpdateUserResponseDTO(updatedUser);
    }

    /**
     * Exclui um usuário do sistema.
     *
     * @param authenticatedUser Usuário autenticado.
    */
    @Transactional
    public void delete(User authenticatedUser) {
        User user = this.findById(authenticatedUser.getId());
        this.userRepository.deleteById(user.getId());
        return;
    }

 
}
