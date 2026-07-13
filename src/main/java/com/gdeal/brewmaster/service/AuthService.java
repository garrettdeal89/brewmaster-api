package com.gdeal.brewmaster.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gdeal.brewmaster.dto.RegisterRequest;
import com.gdeal.brewmaster.dto.UserAccountDTO;
import com.gdeal.brewmaster.exception.ResourceAlreadyExistsException;
import com.gdeal.brewmaster.model.Role;
import com.gdeal.brewmaster.model.UserAccount;
import com.gdeal.brewmaster.repository.UserAccountRepository;

import com.gdeal.brewmaster.dto.LoginRequest;
import com.gdeal.brewmaster.dto.LoginResponse;
import com.gdeal.brewmaster.exception.InvalidCredentialsException;

@Service
public class AuthService {
    
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(

        UserAccountRepository userAccountRepository,
        PasswordEncoder passwordEncoder, JwtService jwtService) {

            this.userAccountRepository = userAccountRepository;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
        }

        public UserAccountDTO register(RegisterRequest request) {

            if (userAccountRepository.existsByUsername(request.getUsername())) {

                throw new ResourceAlreadyExistsException(

                    "User",
                    "email",
                    request.getEmail()
                );
            }

            UserAccount userAccount = new UserAccount(

                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
            );

            UserAccount savedUser = userAccountRepository.save(userAccount);

            return toDTO(savedUser);
        }

    public LoginResponse login(LoginRequest request) {

        UserAccount userAccount = userAccountRepository
            .findByUsername(request.getUsername())
            .orElseThrow(InvalidCredentialsException::new);
        
        boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            userAccount.getPasswordHash()
        );

        if (!passwordMatches) {

            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateToken(userAccount);

        return new LoginResponse(
            accessToken,
            "Bearer",
            jwtService.getExpirationSeconds()
        );
    }
        private UserAccountDTO toDTO(UserAccount userAccount) {

            return new UserAccountDTO(

                userAccount.getId(),
                userAccount.getUsername(),
                userAccount.getEmail(),
                userAccount.getRole()
            );
        }
    }
