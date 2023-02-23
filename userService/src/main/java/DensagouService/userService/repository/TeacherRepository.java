package DensagouService.userService.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import DensagouService.userService.domain.Teacher;

@Repository(value = "teacherRepository")
public interface TeacherRepository extends UserBaseRepository<Teacher, Long> {

	Optional<Teacher> findByMatricule(String matricule);

}
