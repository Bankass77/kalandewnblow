package DensagouService.userService.service;

import java.util.Optional;
import java.util.Set;

import DensagouService.userService.domain.Student;
import DensagouService.userService.request.CreateStudentParameters;
import DensagouService.userService.request.EditStudentParameters;

public interface StudentService extends UserService<Student, Long> {

	/**
	 * @param ineNumber
	 * @return
	 */
	Optional<Student> getStudentByIneNumber(String ineNumber);

	/**
	 * @param id
	 * @param parameters
	 * @return
	 */
	Student editUser(Long id, EditStudentParameters parameters);

	/**
	 * @param parameters
	 * @return
	 */
	Student createStudent(CreateStudentParameters parameters);

}
