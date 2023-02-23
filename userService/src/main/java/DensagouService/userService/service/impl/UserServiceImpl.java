package DensagouService.userService.service.impl;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.repository.UserBaseRepository;
import DensagouService.userService.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service("userService")
@jakarta.transaction.Transactional
@Slf4j
public abstract class UserServiceImpl<T extends Utilisateur, ID extends Serializable> implements UserService<T, ID> {

	private final UserBaseRepository<T, ID> userBaseRepository;

	@Autowired
	public UserServiceImpl(UserBaseRepository<T, ID> userRepository) {
		super();
		this.userBaseRepository = userRepository;

	}

	@Override
	public boolean userWithEmailExists(Email email) {

		log.debug("userWithEmailExists {}", userBaseRepository.existsByEmail(email));
		return userBaseRepository.existsByEmail(email);
	}

	@Override
	public void deleteUserById(Long userId) {

		log.debug("Delete user with id {}", String.valueOf(userId));
		userBaseRepository.deleteById((ID) userId);

	}

	@Override
	public long countUsers() {

		log.debug("Count all user with {}", userBaseRepository.count());
		return userBaseRepository.count();
	}

	@Override
	public void deleteAllUsers() {
		userBaseRepository.deleteAll();

	}

	@Override
	public Page<T> getUsers(Pageable pageable) {

		log.debug("Page  all user  {}", userBaseRepository.findAll(pageable));
		return userBaseRepository.findAll(pageable);
	}

	@Override
	public Optional<T> findByEmail(Email email) {

		log.debug("Find all user with Email {}", userBaseRepository.findByEmail(email));
		return userBaseRepository.findByEmail(email);
	}

	@Override
	public Optional<T> getUserById(Long userId) {
		log.debug("Find user with a id {}", userBaseRepository.findById((ID) userId));
		return userBaseRepository.findById((ID) userId);
	}

}
