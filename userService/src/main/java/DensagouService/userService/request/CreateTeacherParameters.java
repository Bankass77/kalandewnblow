package DensagouService.userService.request;

import java.time.LocalDateTime;
import java.util.Set;

import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public sealed class CreateTeacherParameters extends CreateUserParameters permits EditTeacherParameters {

	String matricule;

	public CreateTeacherParameters(UserName userName, Gender gender, MaritalStatus maritalStatus, Email email,
			String password, PhoneNumber phoneNumber, Address address, LocalDateTime createdDate,
			LocalDateTime modifyDate, Set<UserRole> roles, String matricule) {
		super(userName, gender, maritalStatus, email, password, phoneNumber, address, createdDate, modifyDate, roles);
		this.matricule = matricule;

	}

}
