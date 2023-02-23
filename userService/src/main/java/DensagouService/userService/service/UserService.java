package DensagouService.userService.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.Utilisateur;

public interface UserService<T extends Utilisateur, ID extends Serializable> {

	/**
	 * @param pageable
	 * @return
	 */
	Page<T> getUsers(Pageable pageable);

	/**
	 * @param email
	 * @return
	 */
	boolean userWithEmailExists(Email email);

	/**
	 * @param email
	 * @return
	 */
	Optional<T> findByEmail(Email email);

	/**
	 * @param userId
	 * @return
	 */
	Optional<T> getUserById(Long userId);

	/**
	 * @param userId
	 */
	void deleteUserById(Long userId);

	long countUsers();

	void deleteAllUsers();

	Utilisateur saveUser(Utilisateur user);

}
