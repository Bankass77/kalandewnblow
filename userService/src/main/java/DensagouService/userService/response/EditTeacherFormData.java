package DensagouService.userService.response;

import java.util.Base64;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.Teacher;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.request.EditTeacherParameters;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public final class EditTeacherFormData extends CreateUserFormData {

	private String avatar;

	private String matricule;
	private long version;

	public static EditTeacherFormData fromTeacher(Teacher teacher) {

		EditTeacherFormData result = new EditTeacherFormData();

		result.setAddress(teacher.getAddress());
		result.setCreatedDate(teacher.getCreatedDate());
		result.setEmail(teacher.getEmail().asString());
		result.setGender(teacher.getGender());
		result.setMaritalStatus(teacher.getMaritalStatus());
		result.setMatricule(teacher.getMatricule());
		result.setModifyDate(teacher.getLastModifiedDate());
		result.setPassword(teacher.getPassword());
		result.setPasswordRepeated(teacher.getPassword());
		result.setPhoneNumber(teacher.getPhoneNumber());
		result.setRoles(teacher.getRoles());
		result.setFirstName(teacher.getUserName().getFirstName());
		result.setLastName(teacher.getUserName().getLastName());

		if (teacher.getAvatar() != null) {

			String encoded = Base64.getEncoder().encodeToString(teacher.getAvatar());
			result.setAvatar(encoded);
		}
		return result;

	}

	public EditTeacherParameters toParameters() {

		EditTeacherParameters parameters = new EditTeacherParameters();

		parameters.setAddress(getAddress());
		parameters.setCreatedDate(getCreatedDate());
		parameters.setEmail(new Email(getEmail()));
		parameters.setGender(getGender());
		parameters.setMaritalStatus(getMaritalStatus());
		parameters.setMatricule(getMatricule());
		parameters.setModifyDate(getModifyDate());
		parameters.setPassword(getPassword());
		parameters.setPhoneNumber(getPhoneNumber());
		parameters.setRoles(getRoles());

		parameters.setUserName(new UserName(getFirstName(), getLastName()));

		if (getAvatarFile() != null && !getAvatarFile().isEmpty()) {

			parameters.setAvatar(getAvatarFile());
		}
		return parameters;

	}
}
