package DensagouService.userService.response;

import java.time.LocalDateTime;
import java.util.Base64;

import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.request.EditUserParameters;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public non-sealed class EditUserFormData extends AbstractUserFormData {
	private Long id;
	private String avatarBase64Encoded;
	private long version;

	public static EditUserFormData fromUser(Utilisateur user) {

		EditUserFormData edit = new EditUserFormData();

		edit.setAddress(user.getAddress());
		edit.setCreatedDate(user.getCreatedDate());
		edit.setEmail(user.getEmail().asString());
		edit.setFirstName(user.getUserName().getFirstName());
		edit.setLastName(user.getUserName().getLastName());
		edit.setGender(user.getGender());
		edit.setMaritalStatus(user.getMaritalStatus());
		edit.setModifyDate(user.getLastModifiedDate());
		edit.setPhoneNumber(user.getPhoneNumber());
		edit.setRoles(user.getRoles());

		edit.setVersion(user.getVersion());

		if (user.getAvatar() != null) {

			String encoded = Base64.getEncoder().encodeToString(user.getAvatar());
			edit.setAvatarBase64Encoded(encoded);
		}

		return edit;

	}

	public EditUserParameters toUserParameters() {

		EditUserParameters parameters = new EditUserParameters(version, new UserName(getFirstName(), getLastName()),
				getGender(), getMaritalStatus(), new Email(getEmail()), new PhoneNumber(getPhoneNumber().asString()),
				getAddress(), LocalDateTime.now(), LocalDateTime.now(), getRoles());

		if (getAvatarFile() != null && !getAvatarFile().isEmpty()) {
			parameters.setAvatar(getAvatarFile());
		}

		return parameters;
	}
}