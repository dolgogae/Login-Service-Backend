package local.sihun.springsec.domain.user.repository;

import local.sihun.springsec.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
