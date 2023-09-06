package local.sihun.springsec.domain.user.data;

import local.sihun.springsec.domain.user.UserRole;
import local.sihun.springsec.domain.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "ACCESS_TOKEN", length = 2000)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 2000)
    private String refreshToken;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private UserEntity(String username, String email, String password, UserRole userRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public static UserEntity create(UserDto userDto){
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .userRole(UserRole.valueOf(userDto.getRole()))
                .build();
    }

    public UserEntity setTokens(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        return this;
    }
}
