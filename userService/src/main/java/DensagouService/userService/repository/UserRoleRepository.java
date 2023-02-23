package DensagouService.userService.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DensagouService.userService.domain.Role;
import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.enums.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByUserRole(UserRole userRole);

	List<Utilisateur> findByUserRoleIn(Collection<String> names, Pageable pageable);

}
