package DensagouService.userService.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.Utilisateur;

@Repository
public interface UserRepository<T extends Utilisateur, ID> extends JpaRepository<T, ID> {

	Optional<T> findByEmail(Email email);

	boolean existsByEmail(Email email);

}
