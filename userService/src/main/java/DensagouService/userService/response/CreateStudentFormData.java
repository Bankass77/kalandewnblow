package DensagouService.userService.response;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import DensagouService.userService.constraint.PasswordsMatch;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.request.CreateStudentParameters;
import DensagouService.userService.validation.ValidationGroupOne;
import DensagouService.userService.validation.ValidationGroupTwo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@PasswordsMatch(groups = ValidationGroupTwo.class)
public class CreateStudentFormData extends CreateUserFormData {

	@NotNull(message = "Date de Naissance est obligatoire", groups = ValidationGroupOne.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private int age;

	private String ineNumber;

	@NotNull(groups = ValidationGroupOne.class)
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String motherFirstName;

	@NotNull
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String motherLastName;

	@Pattern(regexp = "^(?:(?:\\+|00)223)\\s*[6-7]{2}\\d{6,8}$", groups = ValidationGroupOne.class)
	private String motherMobile;

	@NotNull
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String fatherLastName;
	@NotNull
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String fatherFirstName;

	@Pattern(regexp = "^(?:(?:\\+|00)223)\\s*[6-7]{2}\\d{6,8}$", groups = ValidationGroupOne.class)
	private String fatherMobile;
	private Set<UserRole> roles;

	public CreateStudentParameters toStudentParameters() {

		CreateStudentParameters parameters = new CreateStudentParameters(new UserName(getFirstName(), getLastName()),
				getGender(), getMaritalStatus(), new Email(getEmail()), getPassword(),
				new PhoneNumber(getPhoneNumber().asString()), getAddress(), getCreatedDate(), getModifyDate(),
				getBirthDate(), getAge(), getIneNumber(), getMotherFirstName(), getMotherLastName(),
				new PhoneNumber(getMotherMobile()), getFatherFirstName(), getFatherLastName(),
				new PhoneNumber(getFatherMobile()), getRoles());

		if (getAvatarFile() != null && !getAvatarFile().isEmpty()) {

			parameters.setAvatar(getAvatarFile());
		}
		return parameters;
	}

}
