package DensagouService.userService.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.repository.UserRoleRepository;
import DensagouService.userService.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
		super();
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public List<Utilisateur> getUtilisationRolesIn(Collection<String> names, Pageable pageable) {

		return userRoleRepository.findByUserRoleIn(names, pageable);
	}

}
