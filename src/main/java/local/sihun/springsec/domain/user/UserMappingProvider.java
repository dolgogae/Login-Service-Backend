package local.sihun.springsec.domain.user;

import local.sihun.springsec.domain.user.data.UserEntity;
import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.dto.UserRequestDto;
import local.sihun.springsec.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserMappingProvider {

    private final ModelMapper mapper;

    public UserDto requestDtoToDto(UserRequestDto requestDto){
        return mapper.map(requestDto, UserDto.class);
    }
    public UserResponseDto userDtoToResponseDto(UserDto userDto){
        return mapper.map(userDto, UserResponseDto.class);
    }
    public UserDto userEntityToUserDto(UserEntity user){
        return mapper.map(user, UserDto.class);
    }
}
