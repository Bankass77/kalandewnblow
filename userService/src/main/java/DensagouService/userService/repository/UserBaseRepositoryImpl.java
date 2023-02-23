package DensagouService.userService.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
public class UserBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements UserBaseRepository<T, ID> {

	private final EntityManager entityManager;

	public UserBaseRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T getOne(ID id) {

		return null;
	}

	@Override
	public T getById(ID id) {

		return null;
	}

	@Override
	public T getReferenceById(ID id) {

		return null;
	}

	@Override
	public Optional<T> findById(ID id) {

		return null;
	}

	@Override
	public boolean existsById(ID id) {

		return false;
	}

	@Override
	public void deleteById(ID id) {

	}

	@Override
	public Optional<T> findByEmail(Email email) {

		return null;
	}

	@Override
	public boolean existsByEmail(Email email) {

		return false;
	}

	@Override
	public Optional<T> findByUserName(UserName userName) {

		return null;
	}

	@Override
	public Optional<T> findByPhoneNumber(PhoneNumber phoneNumber) {

		return null;
	}

}
