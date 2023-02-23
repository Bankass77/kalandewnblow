package DensagouService.userService.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.Student;
import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import DensagouService.userService.repository.StudentRepository;
import DensagouService.userService.request.CreateStudentParameters;
import DensagouService.userService.request.EditStudentParameters;
import DensagouService.userService.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service("studentService")
@Slf4j
@Transactional
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class StudentServiceImpl implements StudentService {

	@Qualifier("studentRepository")
	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;

	}

	@Override
	public Student editUser(Long id, EditStudentParameters parameters) {

		Student stOptional = getUserById(id).orElseThrow(() -> new KaladewnManagementException()
				.throwException(EntityType.STUDENT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id)));

		if (parameters.getVersion() != stOptional.getVersion()) {

			throw new ObjectOptimisticLockingFailureException(Utilisateur.class, stOptional.getId());
		}
		stOptional.setAddress(parameters.getAddress());
		stOptional.setAge(parameters.getAge());
		stOptional.setBirthDate(parameters.getBirthday());

		stOptional.setCreatedDate(parameters.getCreatedDate());
		stOptional.setEmail(parameters.getEmail());
		stOptional.setFatherFirstName(parameters.getFatherFirstName());
		stOptional.setFatherLastName(parameters.getFatherLastName());
		stOptional.setFatherMobile(parameters.getFatherMobile());
		stOptional.setGender(parameters.getGender());

		stOptional.setIneNumber(parameters.getStudentIneNumber());
		stOptional.setLastModifiedDate(parameters.getModifyDate());
		stOptional.setMaritalStatus(parameters.getMaritalStatus());
		stOptional.setMotherFirstName(parameters.getMotherFirstName());
		stOptional.setMotherLastName(parameters.getMotherLastName());
		stOptional.setMotherMobile(parameters.getMotherMobile());
		stOptional.setPassword(parameters.getPassword());
		stOptional.setPhoneNumber(parameters.getPhoneNumber());
		stOptional.setRoles(parameters.getRoles());
		stOptional.setUserName(parameters.getUserName());

		parameters.updateStudent(stOptional);

		return stOptional;
	}

	@Override
	public Page<Student> getUsers(Pageable pageable) {

		return studentRepository.findAll(pageable);
	}

	@Override
	public boolean userWithEmailExists(Email email) {

		return studentRepository.existsByEmail(email);
	}

	@Override
	public Optional<Student> findByEmail(Email email) {

		return studentRepository.findByEmail(email);
	}

	@Override
	public Optional<Student> getUserById(Long userId) {

		return studentRepository.findById(userId);
	}

	@Override
	public void deleteUserById(Long userId) {

		studentRepository.deleteById(userId);
	}

	@Override
	public long countUsers() {

		return studentRepository.count();
	}

	@Override
	public void deleteAllUsers() {
		studentRepository.deleteAll();
	}

	@Override
	public Optional<Student> getStudentByIneNumber(String ineNumber) {

		return studentRepository.findByIneNumber(ineNumber);
	}

	@Override
	public Student createStudent(CreateStudentParameters parameters) {
		log.debug("Creating Student {} ({})", parameters.getUserName().getFullName(), parameters.getEmail().asString());
		String encodedPassword = parameters.getPassword();

		Set<UserRole> stuRoles = new HashSet<>();
		stuRoles.add(UserRole.STUDENT);
		Student student = Student.createStudent(parameters.getUserName(), parameters.getGender(),
				parameters.getMaritalStatus(), parameters.getCreatedDate(), parameters.getModifyDate(),
				parameters.getPhoneNumber(), parameters.getEmail(), parameters.getAddress(), stuRoles, encodedPassword,
				parameters.getStudentIneNumber(), parameters.getBirthday(), parameters.getAge(),
				parameters.getMotherFirstName(), parameters.getMotherLastName(), parameters.getFatherLastName(),
				parameters.getFatherFirstName(), parameters.getFatherMobile(), parameters.getMotherMobile());

		storeAvatarIfPresent(parameters, student);

		return studentRepository.save(student);
	}

	// tag::storeAvatarIfPresent[]
	/**
	 * @param parameters
	 * @param student
	 */
	private void storeAvatarIfPresent(CreateStudentParameters parameters, Utilisateur user) {

		MultipartFile avatar = parameters.getAvatar();

		if (avatar != null) {

			try {
				user.setAvatar(avatar.getBytes());
			} catch (Exception e) {

				throw new KaladewnManagementException().throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND,
						e.getMessage());
			}
		}
	}
	// end::storeAvatarIfPresent[]

	@Override
	public Utilisateur saveUser(Utilisateur user) {

		return null;
	}

}
