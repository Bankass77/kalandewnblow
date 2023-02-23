package DensagouService.userService.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import DensagouService.userService.domain.Student;

@Repository(value = "studentRepository")
public interface StudentRepository extends UserBaseRepository<Student, Long> {

	Optional<Student> findByIneNumber(String ineNumber);

}
