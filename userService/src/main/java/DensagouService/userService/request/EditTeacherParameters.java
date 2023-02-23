package DensagouService.userService.request;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Teacher;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = false)
public final class EditTeacherParameters extends CreateTeacherParameters {

	// tag::update
	public void updateTeacher(Teacher teacher) {

		teacher.setAddress(getAddress());
		teacher.setCreatedDate(getCreatedDate());
		teacher.setEmail(getEmail());
		teacher.setGender(getGender());
		teacher.setLastModifiedDate(getCreatedDate());
		teacher.setMaritalStatus(getMaritalStatus());
		teacher.setLastModifiedDate(getModifyDate());
		teacher.setRoles(null);

		teacher.setMatricule(getMatricule());
		teacher.setPassword(getPassword());
		teacher.setPhoneNumber(getPhoneNumber());
		teacher.setUserName(getUserName());

		MultipartFile avatar = getAvatar();
		if (avatar != null) {

			try {
				teacher.setAvatar(avatar.getBytes());
			} catch (IOException e) {

				throw new KaladewnManagementException().throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND,
						e.getMessage());
			}
		}

	}
}
// end::update[]
