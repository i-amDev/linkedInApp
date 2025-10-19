package com.project.linkedin.user_service.service;

import com.project.linkedin.user_service.dto.LoginRequestDto;
import com.project.linkedin.user_service.dto.SignupRequestDto;
import com.project.linkedin.user_service.dto.UserDto;
import com.project.linkedin.user_service.entity.UserEntity;
import com.project.linkedin.user_service.exception.BadRequestException;
import com.project.linkedin.user_service.exception.ResourceNotFoundException;
import com.project.linkedin.user_service.repository.UserRepository;
import com.project.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    public UserDto signUp(SignupRequestDto signupRequestDto) {

        boolean userAlreadyExist = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (userAlreadyExist) throw new BadRequestException("User already exists, cannot signup again.");

        UserEntity entity = modelMapper.map(signupRequestDto, UserEntity.class);

        entity.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));
        UserEntity savedEntity = userRepository.save(entity);

        return modelMapper.map(savedEntity, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + loginRequestDto.getEmail()));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), userEntity.getPassword());

        if (!isPasswordMatch) throw new BadRequestException("Incorrect Password.");

        return jwtService.generateToken(userEntity);
    }
}
