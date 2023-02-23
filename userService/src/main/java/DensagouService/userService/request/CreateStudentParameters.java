package DensagouService.userService.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.util.CalculateUserAge;
import DensagouService.userService.util.KaladewnUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public sealed class CreateStudentParameters extends CreateUserParameters permits EditStudentParameters {

	private LocalDate birthday;
	private int age;
	private String studentIneNumber;
	private String motherFirstName;
	private String motherLastName;
	private PhoneNumber motherMobile;
	private String fatherLastName;
	private String fatherFirstName;
	private PhoneNumber fatherMobile;

	private Set<UserRole> roles;

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
	 * @param birthday
	 * @param age
	 * @param studentIneNumber
	 * @param motherFirstName
	 * @param motherLastName
	 * @param motherMobile
	 * @param fatherLastName
	 * @param fatherFirstName
	 * @param fatherMobile
	 * @param group
	 * @param courses
	 * @param roles
	 */
	public CreateStudentParameters(UserName userName, Gender gender, MaritalStatus maritalStatus, Email email,
			String password, PhoneNumber phoneNumber, Address address, LocalDateTime createdDate,
			LocalDateTime modifyDate, LocalDate birthday, int age, String studentIneNumber, String motherFirstName,
			String motherLastName, PhoneNumber motherMobile, String fatherLastName, String fatherFirstName,
			PhoneNumber fatherMobile, Set<UserRole> roles) {

		super(userName, gender, maritalStatus, email, password, phoneNumber, address, createdDate, modifyDate, roles);

		this.birthday = birthday;
		this.age = CalculateUserAge.calculateAge(birthday);
		this.fatherFirstName = fatherFirstName;
		this.fatherLastName = fatherLastName;
		this.fatherMobile = fatherMobile;
		this.motherFirstName = motherFirstName;
		this.motherLastName = motherLastName;
		this.motherMobile = motherMobile;

		this.roles = roles;
		this.studentIneNumber = KaladewnUtility.generatingandomAlphaNumericStringWithFixedLength();
	}

}
