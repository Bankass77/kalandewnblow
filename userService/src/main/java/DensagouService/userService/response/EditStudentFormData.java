package DensagouService.userService.response;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.Student;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.request.EditStudentParameters;
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
public final class EditStudentFormData extends CreateUserFormData {

	private Long id;
	private String avatarBase64Encoded;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private int age;
	private String ineNumber;
	private String motherFirstName;
	private String motherLastName;
	private String motherMobile;
	private String fatherLastName;
	private String fatherFirstName;
	private String fatherMobile;
	private long version;
	private Set<UserRole> roles;

	// tag::fromStudent[]
	/**
	 * 
	 * @param student
	 * @return
	 */
	public static EditStudentFormData fromStudent(Student student) {
		EditStudentFormData result = new EditStudentFormData();

		result.setId(student.getId());
		result.setAddress(student.getAddress());
		result.setFirstName(student.getUserName().getFirstName());
		result.setLastName(student.getUserName().getLastName());
		result.setVersion(student.getVersion());
		result.setPassword(student.getPassword());
		result.setPasswordRepeated(student.getPassword());

		result.setCreatedDate(student.getCreatedDate());
		result.setModifyDate(student.getLastModifiedDate());

		result.setGender(student.getGender());
		result.setMaritalStatus(student.getMaritalStatus());
		result.setPhoneNumber(student.getPhoneNumber());
		result.setEmail(student.getEmail().asString());

		result.setAge(student.getAge());
		result.setBirthDate(student.getBirthDate());

		result.setFatherFirstName(student.getFatherFirstName());
		result.setFatherLastName(student.getFatherLastName());
		result.setFatherMobile(student.getFatherMobile().asString());

		result.setMotherFirstName(student.getMotherFirstName());
		result.setMotherLastName(student.getMotherLastName());
		result.setMotherMobile(student.getMotherMobile().asString());
		result.setIneNumber(student.getIneNumber());
		result.setRoles(student.getRoles());

		if (student.getAvatar() != null) {

			String encoded = Base64.getEncoder().encodeToString(student.getAvatar());
			result.setAvatarBase64Encoded(encoded);
		}

		return result;
	}

	// end::fromStudent[]

	// tag::toParameters[]

	public EditStudentParameters toStudentParameters() {

		EditStudentParameters parameters = new EditStudentParameters(version,
				new UserName(getFirstName(), getLastName()), getGender(), getMaritalStatus(), new Email(getEmail()),
				getPassword(), new PhoneNumber(getPhoneNumber().asString()), getAddress(), getCreatedDate(),
				getModifyDate(), getBirthDate(), getAge(), getIneNumber(), getMotherFirstName(), getMotherLastName(),
				new PhoneNumber(getMotherMobile()), getFatherLastName(), getFatherFirstName(),
				new PhoneNumber(getFatherMobile()), getRoles());

		if (getAvatarFile() != null && !getAvatarFile().isEmpty()) {

			parameters.setAvatar(getAvatarFile());
		}

		return parameters;

	}

	// end::toParameters[]

}
