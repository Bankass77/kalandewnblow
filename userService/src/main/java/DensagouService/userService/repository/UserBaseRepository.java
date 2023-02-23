package DensagouService.userService.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;

@NoRepositoryBean
public interface UserBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	Optional<T> findByEmail(Email email);

	boolean existsByEmail(Email email);

	Optional<T> findByUserName(UserName userName);

	Optional<T> findByPhoneNumber(PhoneNumber phoneNumber);

	Optional<T> findById(ID id);

	Page<T> findAll(Pageable pageable);

}
