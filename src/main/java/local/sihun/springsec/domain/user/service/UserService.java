package local.sihun.springsec.domain.user.service;

import local.sihun.springsec.domain.user.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getUser(UserDto userDto);
    UserDto deleteUser(UserDto userDto);

    UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken);
}
