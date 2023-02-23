package DensagouService.userService.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Teacher;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import DensagouService.userService.repository.TeacherRepository;
import DensagouService.userService.request.CreateTeacherParameters;
import DensagouService.userService.request.EditTeacherParameters;
import DensagouService.userService.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service("teacherService")
@Transactional
@Slf4j
public class TeacherServiceImpl implements TeacherService {

	@Qualifier("teacherRepository")
	private final TeacherRepository teacherRepository;

	/**
	 * @param teacherRepository
	 */
	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public Teacher editTeachetr(Long id, EditTeacherParameters parameters) {

		Optional<Teacher> teacher = teacherRepository.findById(id);

		if (teacher.isPresent()) {

			teacher.get().setAddress(parameters.getAddress());
			teacher.get().setCreatedDate(parameters.getCreatedDate());
			teacher.get().setEmail(parameters.getEmail());
			teacher.get().setGender(parameters.getGender());
			teacher.get().setLastModifiedDate(parameters.getModifyDate());
			teacher.get().setMaritalStatus(parameters.getMaritalStatus());
			teacher.get().setMatricule(parameters.getMatricule());
			teacher.get().setPassword(parameters.getPassword());
			teacher.get().setPhoneNumber(parameters.getPhoneNumber());
			teacher.get().setUserName(parameters.getUserName());

			storeAvatarIfPresent(parameters, teacher.get());
			parameters.updateTeacher(teacher.get());

			log.debug("Update teachetr with id:{}", id);
		}
		return null;

	}

	@Override
	public Teacher createTeacher(CreateTeacherParameters parameters) {

		Teacher teacher = new Teacher();
		teacher.setAddress(parameters.getAddress());
		teacher.setCreatedDate(parameters.getCreatedDate());
		teacher.setEmail(parameters.getEmail());
		teacher.setGender(parameters.getGender());
		teacher.setLastModifiedDate(parameters.getModifyDate());
		teacher.setMaritalStatus(parameters.getMaritalStatus());
		teacher.setMatricule(parameters.getMatricule());
		teacher.setPassword(parameters.getPassword());
		teacher.setPhoneNumber(parameters.getPhoneNumber());
		teacher.setUserName(parameters.getUserName());
		storeAvatarIfPresent(parameters, teacher);
		log.debug("Teacher created:{}", teacher);
		return teacherRepository.save(teacher);

	}

	@Override
	public Optional<Teacher> findById(Long teacherId) {
		log.debug("Find teacher by id:{}", teacherId);
		return teacherRepository.findById(teacherId);
	}

	@Override
	public Set<Teacher> findAll() {

		return new HashSet<>(teacherRepository.findAll());
	}

	@Override
	public Page<Teacher> findAll(Pageable pageable) {

		return teacherRepository.findAll(pageable);
	}

	@Override
	public Optional<Teacher> findForCourseWithId(Long courseId) {

		log.debug("find teacher For Course With Id:{}", courseId);
		return teacherRepository.findById(courseId);
	}

	@Override
	public boolean existsById(Long teacherId) {

		return teacherRepository.existsById(teacherId);
	}

	// tag::storeAvatarIfPresent[]
	/**
	 * @param parameters
	 * @param teacher
	 */
	private void storeAvatarIfPresent(CreateTeacherParameters parameters, Teacher teacher) {

		MultipartFile avatar = parameters.getAvatar();

		if (avatar != null) {

			try {
				teacher.setAvatar(avatar.getBytes());
			} catch (Exception e) {

				throw new KaladewnManagementException().throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND,
						e.getMessage());
			}
		}
	}
	// end::storeAvatarIfPresent[]

}
