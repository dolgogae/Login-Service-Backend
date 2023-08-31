package local.sihun.springsec.domain.user.service;

import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplV1 implements UserService{

    private final UserJpaRepository userJpaRepository;

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
}
