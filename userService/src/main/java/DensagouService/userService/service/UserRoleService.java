package DensagouService.userService.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import DensagouService.userService.domain.Utilisateur;

public interface UserRoleService {

	List<Utilisateur> getUtilisationRolesIn(Collection<String> names, Pageable pageable);

}
