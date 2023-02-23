package DensagouService.userService.request;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.domain.Utilisateur;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public non-sealed class EditUserParameters extends CreateUserParameters {

	private final long version;

	public EditUserParameters(long version, UserName userName, Gender gender, MaritalStatus maritalStatus, Email email,
			PhoneNumber phoneNumber, Address address, LocalDateTime createdDate, LocalDateTime modifyDate,
			Set<UserRole> roles) {
		super(userName, gender, maritalStatus, email, null, phoneNumber, address, createdDate, modifyDate, roles);

		this.version = version;
	}

	// tag::update
	public void update(Utilisateur user) {
		user.setCreatedDate(getCreatedDate());
		user.setEmail(getEmail());
		user.setGender(getGender());
		user.setLastModifiedDate(getCreatedDate());
		user.setPhoneNumber(getPhoneNumber());
		user.setMaritalStatus(getMaritalStatus());
		user.setAddress(getAddress());
		user.setUserName(getUserName());
		user.setVersion(user.getVersion());
		user.setRoles(getRoles());

		MultipartFile avatar = getAvatar();
		if (avatar != null) {

			try {
				user.setAvatar(avatar.getBytes());
			} catch (IOException e) {

				throw new KaladewnManagementException().throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND,
						e.getMessage());
			}
		}

	}

	// end::update[]

}
