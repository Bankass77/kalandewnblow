package DensagouService.userService.response;

import DensagouService.userService.constraint.NotExistingUser;
import DensagouService.userService.constraint.PasswordsMatch;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.request.CreateUserParameters;
import DensagouService.userService.validation.ValidationGroupTwo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@NotExistingUser(groups = ValidationGroupTwo.class)
@PasswordsMatch(groups = ValidationGroupTwo.class)
@Getter
@Setter
public non-sealed class CreateUserFormData extends AbstractUserFormData {

	@NotBlank
	private String password;
	@NotBlank
	private String passwordRepeated;

	public CreateUserParameters toUserParameters() {

		CreateUserParameters parameters = new CreateUserParameters(new UserName(getFirstName(), getLastName()),
				getGender(), getMaritalStatus(), new Email(getEmail()), password,
				new PhoneNumber(getPhoneNumber().asString()), getAddress(), getCreatedDate(), getModifyDate(),
				getRoles());

		if (getAvatarFile() != null && !getAvatarFile().isEmpty()) {
			parameters.setAvatar(getAvatarFile());
		}

		return parameters;
	}

}
