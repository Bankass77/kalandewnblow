package DensagouService.userService.request;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.Email;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.domain.Student;
import DensagouService.userService.domain.UserName;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = false)
public final class EditStudentParameters extends CreateStudentParameters {

	private Long version;

	public EditStudentParameters(long version, UserName userName, Gender gender, MaritalStatus maritalStatus,
			Email email, String password, PhoneNumber phoneNumber, Address address, LocalDateTime createdDate,
			LocalDateTime modifyDate, LocalDate birthday, int age, String studentIneNumber, String motherFirstName,
			String motherLastName, PhoneNumber motherMobile, String fatherLastName, String fatherFirstName,
			PhoneNumber fatherMobile, Set<UserRole> roles) {
		super(userName, gender, maritalStatus, email, password, phoneNumber, address, createdDate, modifyDate, birthday,
				age, studentIneNumber, motherFirstName, motherLastName, motherMobile, fatherLastName, fatherFirstName,
				fatherMobile, roles);

		this.version = version;

	}

	// tag::update[]
	public void updateStudent(Student student) {

		student.setUserName(getUserName());
		student.setEmail(getEmail());
		student.setPhoneNumber(getPhoneNumber());
		student.setAddress(getAddress());
		student.setBirthDate(getBirthday());
		student.setGender(getGender());
		student.setMaritalStatus(getMaritalStatus());
		student.setIneNumber(getStudentIneNumber());
		student.setAge(getAge());

		student.setCreatedDate(LocalDateTime.now());
		student.setLastModifiedDate(LocalDateTime.now());

		student.setFatherFirstName(getFatherFirstName());
		student.setFatherLastName(getFatherLastName());
		student.setFatherMobile(getFatherMobile());

		student.setMotherFirstName(getMotherFirstName());
		student.setMotherLastName(getMotherLastName());
		student.setMotherMobile(getMotherMobile());
		student.setRoles(getRoles());

		MultipartFile avatar = getAvatar();

		if (avatar != null) {
			try {
				student.setAvatar(avatar.getBytes());
			} catch (IOException e) {

				throw new KaladewnManagementException().throwException(EntityType.STUDENT,
						ExceptionType.DUPLICATE_ENTITY, String.valueOf(student.getId()));
			}

		}

	}
	// end::update[]
}
