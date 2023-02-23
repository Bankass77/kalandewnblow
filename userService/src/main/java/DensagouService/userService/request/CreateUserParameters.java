package DensagouService.userService.request;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public sealed class CreateUserParameters permits EditUserParameters, CreateStudentParameters, CreateTeacherParameters {

	private UserName userName;
	private Gender gender;
	private MaritalStatus maritalStatus;
	private Email email;
	private String password;
	private PhoneNumber phoneNumber;
	private Address address;
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime modifyDate = LocalDateTime.now();
	private Set<UserRole> roles;

	@Nullable
	private MultipartFile avatar;

	/**
	 * 
	 * @param userName
	 * @param gender
	 * @param maritalStatus
	 * @param email
	 * @param password
	 * @param phoneNumber
	 * @param address
	 * @param createdDate
	 * @param modifyDate
	 * @param roles
	 */
	public CreateUserParameters(UserName userName, Gender gender, MaritalStatus maritalStatus, Email email,
			String password, PhoneNumber phoneNumber, Address address, LocalDateTime createdDate,
			LocalDateTime modifyDate, Set<UserRole> roles) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdDate = createdDate;
		this.modifyDate = modifyDate;
		this.roles = roles;
	}

}
