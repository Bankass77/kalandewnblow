package DensagouService.userService.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import DensagouService.userService.domain.Teacher;
import DensagouService.userService.request.CreateTeacherParameters;
import DensagouService.userService.request.EditTeacherParameters;

public interface TeacherService {

	Teacher editTeachetr(Long id, EditTeacherParameters parameters);

	/**
	 * @param parameters
	 * @return
	 */
	Teacher createTeacher(CreateTeacherParameters parameters);

	/**
	 * Returns teacher with given identifier if any.
	 */
	Optional<Teacher> findById(Long teacherId);

	/**
	 * Finds all available teachers.
	 */
	Set<Teacher> findAll();

	/**
	 * Finds all available teachers for given pageable data
	 */
	Page<Teacher> findAll(Pageable pageable);

	/**
	 * Finds teacher assigned to the course with given identifier if any.
	 */
	Optional<Teacher> findForCourseWithId(Long courseId);

	/**
	 * Checks whether teacher with specified identifier exists
	 * 
	 * @return {@code true} if such teacher exists, {@code false} otherwise
	 */
	boolean existsById(Long teacherId);

}