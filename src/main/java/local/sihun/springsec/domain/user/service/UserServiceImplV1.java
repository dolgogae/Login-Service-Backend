package local.sihun.springsec.domain.user.service;

import local.sihun.springsec.domain.user.UserMappingProvider;
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
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto deleteUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findMemberAndCheckMemberExists(Long id) {
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        UserDto userDto = userMappingProvider.userEntityToUserDto(user);

        return userDto;
    }
}
