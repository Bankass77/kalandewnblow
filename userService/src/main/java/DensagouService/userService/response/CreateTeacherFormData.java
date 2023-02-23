package DensagouService.userService.response;

import DensagouService.userService.constraint.PasswordsMatch;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.request.CreateTeacherParameters;
import DensagouService.userService.validation.ValidationGroupTwo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@PasswordsMatch(groups = ValidationGroupTwo.class)
public final class CreateTeacherFormData extends CreateUserFormData {

	private String matricule;

	public CreateTeacherParameters toTeacherParameters() {

		CreateTeacherParameters parameters = new CreateTeacherParameters();

		parameters.setAddress(getAddress());
		parameters.setCreatedDate(getCreatedDate());
		parameters.setEmail(null);
		parameters.setGender(getGender());
		parameters.setMaritalStatus(getMaritalStatus());
		parameters.setMatricule(matricule);
		parameters.setModifyDate(getModifyDate());
		parameters.setPassword(getPassword());
		parameters.setPhoneNumber(new PhoneNumber(getPhoneNumber().asString()));
		parameters.setRoles(getRoles());
		parameters.setUserName(new UserName(getFirstName(), getLastName()));

		return parameters;

	}
}
