package local.sihun.springsec.domain.user.service;

import local.sihun.springsec.domain.user.UserMappingProvider;
import local.sihun.springsec.domain.user.UserRole;
import local.sihun.springsec.domain.user.data.UserEntity;
import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.repository.UserJpaRepository;
import local.sihun.springsec.global.error.ErrorCode;
import local.sihun.springsec.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplV1 implements UserService{

    private final UserJpaRepository userJpaRepository;
    private final UserMappingProvider userMappingProvider;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setRole(UserRole.ANONYMOUS.name());
        UserEntity userEntity = UserEntity.create(userDto);
        UserEntity savedUser = userJpaRepository.save(userEntity);

        UserDto result = userMappingProvider.userEntityToUserDto(savedUser);
        log.info(result.toString());

        return result;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        UserDto result = userMappingProvider.userEntityToUserDto(userEntity);
        log.info("get User = {}", result);

        return result;
    }

    @Override
    public UserDto deleteUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken) {
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        user.setTokens(accessToken, refreshToken);

        UserEntity savedUser = userJpaRepository.save(user);

        UserDto userDto = userMappingProvider.userEntityToUserDto(savedUser);

        return userDto;
    }
}
